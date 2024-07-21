package com.adkp.fuexchange.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StaffLoginRequest {
    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String numberPhone;

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String password;
}
