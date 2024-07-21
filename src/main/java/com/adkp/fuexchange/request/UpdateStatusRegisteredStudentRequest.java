package com.adkp.fuexchange.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateStatusRegisteredStudentRequest {

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private Integer registeredStudentId;

    private Integer isActive;
}
