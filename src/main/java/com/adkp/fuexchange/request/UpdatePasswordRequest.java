package com.adkp.fuexchange.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdatePasswordRequest {

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private Integer idWantUpdate;

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String oldPassword;

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String newPassword;

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String confirmNewPassword;
}
