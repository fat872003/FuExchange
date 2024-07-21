package com.adkp.fuexchange.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ContactToRequest {

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private Integer registeredStudentId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private Integer sellerId;

    @NotEmpty(message = "Vui lòng nhập đầy đủ thông tin!")
    private String content;
}
