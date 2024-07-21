package com.adkp.fuexchange.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class SendReportSellerRequest {

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int registeredStudentId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int sellerId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int reportSellerTypeId;

    private String content;
}
