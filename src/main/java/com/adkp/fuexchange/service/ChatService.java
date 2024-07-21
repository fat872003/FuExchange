package com.adkp.fuexchange.service;

import com.adkp.fuexchange.dto.ChatMessageDTO;
import com.adkp.fuexchange.dto.ChatRoomDTO;
import com.adkp.fuexchange.request.ChatRequest;
import com.adkp.fuexchange.request.ContactToRequest;

import java.util.List;

public interface ChatService {

    List<ChatRoomDTO> getChatRoomByRegisteredStudentId(Integer registeredStudentId);

    ChatMessageDTO sendChatMessage(ChatRequest chatRequest);

    ChatRoomDTO getChatRoomStudentToStudent(Integer studentSendId, Integer studentReceiveId);

    ChatMessageDTO contactToSeller(ContactToRequest contactToRequest);

    ChatMessageDTO contactToStudent(ContactToRequest contactToRequest);

    ChatRoomDTO deleteChatRoom(int chatRoomId);
}
