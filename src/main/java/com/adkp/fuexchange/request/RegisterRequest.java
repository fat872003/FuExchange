package com.adkp.fuexchange.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String studentId;

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String identifyNumber;

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String password;

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String confirmPassword;
}
