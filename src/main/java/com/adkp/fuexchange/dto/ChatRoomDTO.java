package com.adkp.fuexchange.dto;

import lombok.Data;

import java.util.List;
@Data
public class ChatRoomDTO {

    private int chatRoomId;

    private boolean active;

    private List<ChatMessageDTO> chatMessage;
}
