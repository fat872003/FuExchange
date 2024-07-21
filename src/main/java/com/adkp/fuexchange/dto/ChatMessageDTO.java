package com.adkp.fuexchange.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatMessageDTO {

    private int chatMessageId;

    private int chatRoom;

    private int studentSendId;

    private int studentReceiveId;

    private String content;

    private LocalDateTime timeSend;
}
