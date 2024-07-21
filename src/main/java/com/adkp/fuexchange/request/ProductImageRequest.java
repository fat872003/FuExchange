package com.adkp.fuexchange.request;

import com.adkp.fuexchange.pojo.ProductDetail;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductImageRequest {

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String imageUrl;
}
