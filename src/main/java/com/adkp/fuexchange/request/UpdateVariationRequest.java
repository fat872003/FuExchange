package com.adkp.fuexchange.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class UpdateVariationRequest {

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int variationId;

    @NotEmpty(message = "Vui lòng nhập đầy đủ thông tin!")
    private String variationName;

    private List<@Valid UpdateVariationDetailRequest> variationDetail;
}
