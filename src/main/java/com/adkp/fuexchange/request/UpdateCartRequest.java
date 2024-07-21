package com.adkp.fuexchange.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class UpdateCartRequest {

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin")
    private int cartId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin")
    private int postProductId;

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private List<Integer> variationDetailId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin")
    private int quantity;
}
