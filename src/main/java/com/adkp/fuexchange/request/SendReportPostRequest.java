package com.adkp.fuexchange.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class SendReportPostRequest {

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int registeredStudentId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int postProductId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int reportProductTypeId;

    private String content;
}
