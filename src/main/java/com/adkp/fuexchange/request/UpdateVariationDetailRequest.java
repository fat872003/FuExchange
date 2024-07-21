package com.adkp.fuexchange.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UpdateVariationDetailRequest {

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int variationDetailId;

    @NotEmpty(message = "Vui lòng nhập đầy đủ thông tin!")
    private String description;
}
