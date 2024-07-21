package com.adkp.fuexchange.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdatePostProductRequest {

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int postProductId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private Integer productId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private Integer postTypeId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private Integer campusId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private Integer postStatusId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int quantity;

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String content;
}
