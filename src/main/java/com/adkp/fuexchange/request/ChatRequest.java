package com.adkp.fuexchange.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class ChatRequest {

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private Integer studentSendId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private Integer studentReceiveId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private Integer chatRoomId;

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String content;
}
