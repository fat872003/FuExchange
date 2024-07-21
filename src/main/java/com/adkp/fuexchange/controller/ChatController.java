package com.adkp.fuexchange.controller;

import com.adkp.fuexchange.dto.ChatMessageDTO;
import com.adkp.fuexchange.dto.ChatRoomDTO;
import com.adkp.fuexchange.request.ChatRequest;
import com.adkp.fuexchange.request.ContactToRequest;
import com.adkp.fuexchange.response.ResponseObject;
import com.adkp.fuexchange.service.ChatService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@Tag(name = "Chat")
@Validated
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat-room/{registeredStudentId}")
    public ResponseObject<Object> getChatRoomByRegisteredStudentId(@PathVariable int registeredStudentId) {

        int status = HttpStatus.OK.value();
        String message = HttpStatus.OK.name();
        String content = "Xem thông tin thành công!";

        List<ChatRoomDTO> chatRoomDTO = chatService.getChatRoomByRegisteredStudentId(registeredStudentId);

        if (chatRoomDTO == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR.value();
            message = (HttpStatus.INTERNAL_SERVER_ERROR.name());
            content = "Xem thông tin thất bại!";
        }

        return ResponseObject.builder()
                .status(status)
                .message(message)
                .content(content)
                .data(chatRoomDTO)
                .build();
    }

    @GetMapping("/chat-room/student-to-student")
    public ResponseObject<Object> getChatRoomByRegisteredStudentIdAndSellerId(
            @RequestParam("studentSendId") int studentSendId,
            @RequestParam("studentReceiveId") int studentReceiveId
    ) {

        String content = "Xem thông tin thành công!";

        ChatRoomDTO chatRoom = chatService.getChatRoomStudentToStudent(studentSendId, studentReceiveId);

        if (chatRoom == null) {
            content = "Đoạn chat đã bị xóa hoặc chưa có tin nhắn nào được gửi!";
        }

        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content(content)
                .data(chatRoom)
                .build();
    }

    @PostMapping("/send-message")
    public ResponseObject<Object> sendMessage(@RequestBody @Valid ChatRequest chatRequest) {

        int status = HttpStatus.OK.value();
        String message = (HttpStatus.OK.name());
        String content = "Gửi tin nhắn thành công!";

        ChatMessageDTO chatMessage = chatService.sendChatMessage(chatRequest);

        if (chatMessage == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR.value();
            message = (HttpStatus.INTERNAL_SERVER_ERROR.name());
            content = "Gửi tin nhắn thất bại!";
        }

        return ResponseObject.builder()
                .status(status)
                .message(message)
                .content(content)
                .data(chatMessage)
                .build();
    }

    @PostMapping("/contact-to-seller")
    public ResponseObject<Object> contactToSeller(@RequestBody @Valid ContactToRequest contactToSellerRequest) {

        int status = HttpStatus.OK.value();
        String message = (HttpStatus.OK.name());
        String content = "Gửi tin nhắn thành công!";

        ChatMessageDTO chatMessageDTO = chatService.contactToSeller(contactToSellerRequest);

        if (chatMessageDTO == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR.value();
            message = (HttpStatus.INTERNAL_SERVER_ERROR.name());
            content = "Gửi tin nhắn thất bại!";
        }

        return ResponseObject.builder()
                .status(status)
                .message(message)
                .content(content)
                .data(chatMessageDTO)
                .build();
    }

    @PostMapping("/contact-to-student")
    public ResponseObject<Object> contactToStudent(@RequestBody @Valid ContactToRequest contactToRequest) {

        int status = HttpStatus.OK.value();
        String message = (HttpStatus.OK.name());
        String content = "Gửi tin nhắn thành công!";

        ChatMessageDTO chatMessageDTO = chatService.contactToStudent(contactToRequest);

        if (chatMessageDTO == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR.value();
            message = (HttpStatus.INTERNAL_SERVER_ERROR.name());
            content = "Gửi tin nhắn thất bại!";
        }

        return ResponseObject.builder()
                .status(status)
                .message(message)
                .content(content)
                .data(chatMessageDTO)
                .build();
    }

    @PutMapping("/delete-chat-room")
    public ResponseObject<Object> updateStatusChatRoom(
            @RequestBody Integer chatRoomId
    ) {

        int status = HttpStatus.OK.value();
        String message = (HttpStatus.OK.name());
        String content = "Xóa thành công!";

        ChatRoomDTO chatRoomDTO = chatService.deleteChatRoom(chatRoomId);

        if (chatRoomDTO == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR.value();
            message = (HttpStatus.INTERNAL_SERVER_ERROR.name());
            content = "Xóa thất bại!";
        }

        return ResponseObject.builder()
                .status(status)
                .message(message)
                .content(content)
                .data(chatRoomDTO)
                .build();
    }
}
