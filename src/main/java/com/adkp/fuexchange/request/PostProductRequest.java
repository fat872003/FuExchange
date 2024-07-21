package com.adkp.fuexchange.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PostProductRequest {

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int sttOrder;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int postProductId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int sellerId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int variationId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int variationDetailId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int quantity;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private long price;
}
