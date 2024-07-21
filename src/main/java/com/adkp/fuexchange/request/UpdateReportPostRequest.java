package com.adkp.fuexchange.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateReportPostRequest {

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int reportPostProductId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int reportStatusId;
}
