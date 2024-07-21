package com.adkp.fuexchange.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateStatusRequest {

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int sellerId;

    private int isActive;
}
