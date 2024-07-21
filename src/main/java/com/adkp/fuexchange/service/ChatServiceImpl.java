package com.adkp.fuexchange.service;

import com.adkp.fuexchange.dto.ChatMessageDTO;
import com.adkp.fuexchange.dto.ChatRoomDTO;
import com.adkp.fuexchange.mapper.ChatMessageMapper;
import com.adkp.fuexchange.mapper.ChatRoomMapper;
import com.adkp.fuexchange.pojo.ChatMessage;
import com.adkp.fuexchange.pojo.ChatRoom;
import com.adkp.fuexchange.pojo.RegisteredStudent;
import com.adkp.fuexchange.pojo.Seller;
import com.adkp.fuexchange.repository.ChatMessageRepository;
import com.adkp.fuexchange.repository.ChatRoomRepository;
import com.adkp.fuexchange.repository.RegisteredStudentRepository;
import com.adkp.fuexchange.repository.SellerRepository;
import com.adkp.fuexchange.request.ChatRequest;
import com.adkp.fuexchange.request.ContactToRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRoomRepository chatRoomRepository;

    private final ChatMessageRepository chatMessageRepository;

    private final ChatRoomMapper chatRoomMapper;

    private final RegisteredStudentRepository registeredStudentRepository;

    private final SellerRepository sellerRepository;

    private final ChatMessageMapper chatMessageMapper;

    @Autowired
    public ChatServiceImpl(ChatRoomRepository chatRoomRepository, ChatMessageRepository chatMessageRepository, ChatRoomMapper chatRoomMapper, RegisteredStudentRepository registeredStudentRepository, SellerRepository sellerRepository, ChatMessageMapper chatMessageMapper) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatMessageRepository = chatMessageRepository;
        this.chatRoomMapper = chatRoomMapper;
        this.registeredStudentRepository = registeredStudentRepository;
        this.sellerRepository = sellerRepository;
        this.chatMessageMapper = chatMessageMapper;
    }

    @Override
    public List<ChatRoomDTO> getChatRoomByRegisteredStudentId(Integer registeredStudentId) {

        List<ChatRoom> chatRoom = chatRoomRepository.getChatRoomByRegisteredStudentId(registeredStudentId);

        return chatRoomMapper.toChatRoomDTOList(chatRoom);
    }

    @Override
    @Transactional
    public ChatMessageDTO sendChatMessage(ChatRequest chatRequest) {

        ChatRoom chatRoom = saveChatRoom(chatRequest.getChatRoomId());

        RegisteredStudent getStudentSend = getRegisteredStudent(chatRequest.getStudentSendId());

        RegisteredStudent getStudentReceive = getRegisteredStudent(chatRequest.getStudentReceiveId());

        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoomId(chatRoom)
                .studentSendId(getStudentSend)
                .studentReceiveId(getStudentReceive)
                .content(chatRequest.getContent())
                .timeSend(LocalDateTime.now())
                .build();
        return chatMessageMapper.toChatMessageDTO(chatMessageRepository.save(chatMessage));
    }

    @Override
    public ChatRoomDTO getChatRoomStudentToStudent(Integer studentSendId, Integer studentReceiveId) {

        ChatRoom chatRoom = chatRoomRepository.getChatRoomByRegisteredStudentIdAndSellerId(
                studentSendId,
                studentReceiveId
        );

        return chatRoomMapper.toChatRoomDTO(chatRoom);
    }

    @Override
    @Transactional
    public ChatMessageDTO contactToSeller(ContactToRequest contactToRequest) {

        Seller seller = sellerRepository.getReferenceById(contactToRequest.getSellerId());

        ChatRoom chatRoom = chatRoomRepository.getChatRoomByRegisteredStudentIdAndSellerId(
                contactToRequest.getRegisteredStudentId(),
                seller.getRegisteredStudentId().getRegisteredStudentId()
        );

        if (chatRoom == null || !chatRoom.isActive()) {
            chatRoom = chatRoomRepository.save(new ChatRoom(true));
        }

        ChatMessage chatMessage = saveChatMessageContactToSeller(chatRoom, contactToRequest);

        return chatMessageMapper.toChatMessageDTO(chatMessage);
    }

    @Override
    public ChatMessageDTO contactToStudent(ContactToRequest contactToRequest) {
        Seller seller = sellerRepository.getReferenceById(contactToRequest.getSellerId());

        ChatRoom chatRoom = chatRoomRepository.getChatRoomByRegisteredStudentIdAndSellerId(
                seller.getRegisteredStudentId().getRegisteredStudentId(),
                contactToRequest.getRegisteredStudentId()
        );

        if (chatRoom == null) {
            chatRoom = chatRoomRepository.save(new ChatRoom(true));
        }

        ChatMessage chatMessage = saveChatMessageContactToStudent(chatRoom, contactToRequest);

        return chatMessageMapper.toChatMessageDTO(chatMessage);
    }

    @Override
    public ChatRoomDTO deleteChatRoom(int chatRoomId) {

        ChatRoom chatRoom = chatRoomRepository.getReferenceById(chatRoomId);

        chatRoom.setActive(false);

        return chatRoomMapper.toChatRoomDTO(chatRoomRepository.save(chatRoom));
    }

    private ChatMessage saveChatMessageContactToStudent(
            ChatRoom chatRoom,
            ContactToRequest contactToRequest
    ) {
        Seller seller = sellerRepository.getReferenceById(contactToRequest.getSellerId());

        RegisteredStudent studentSend = seller.getRegisteredStudentId();

        RegisteredStudent studentReceive = registeredStudentRepository.getReferenceById(contactToRequest.getRegisteredStudentId());

        return chatMessageRepository.save(
                ChatMessage.builder()
                        .chatRoomId(chatRoom)
                        .studentSendId(studentSend)
                        .studentReceiveId(studentReceive)
                        .content(contactToRequest.getContent())
                        .timeSend(LocalDateTime.now())
                        .build());
    }

    private ChatMessage saveChatMessageContactToSeller(
            ChatRoom chatRoom,
            ContactToRequest contactToRequest
    ) {
        Seller seller = sellerRepository.getReferenceById(contactToRequest.getSellerId());

        RegisteredStudent studentSend = registeredStudentRepository.getReferenceById(contactToRequest.getRegisteredStudentId());

        RegisteredStudent studentReceive = seller.getRegisteredStudentId();

        return chatMessageRepository.save(
                ChatMessage.builder()
                        .chatRoomId(chatRoom)
                        .studentSendId(studentSend)
                        .studentReceiveId(studentReceive)
                        .content(contactToRequest.getContent())
                        .timeSend(LocalDateTime.now())
                        .build());
    }

    private ChatRoom saveChatRoom(Integer chatRoomId) {
        if (chatRoomRepository.existsById(chatRoomId)) {
            return chatRoomRepository.save(chatRoomRepository.getReferenceById(chatRoomId));
        }
        return chatRoomRepository.save(new ChatRoom(true));
    }

    private RegisteredStudent getRegisteredStudent(Integer registeredStudentId) {
        return registeredStudentRepository.getReferenceById(registeredStudentId);
    }

}
