package com.adkp.fuexchange.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegisterToSellerRequest {

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int registeredStudentId;

    @NotEmpty(message = "Vui lòng nhập đầy đủ thông tin!")
    private String password;

    @NotEmpty(message = "Vui lòng nhập đầy đủ thông tin!")
    private String bankingNumber;

    @NotEmpty(message = "Vui lòng nhập đầy đủ thông tin!")
    private String bankingName;

}
