package com.adkp.fuexchange.request;

import com.adkp.fuexchange.pojo.Orders;
import com.adkp.fuexchange.pojo.PostProduct;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterReviewRequest {
    private int postProductId;
    private int orderId;
    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private int ratingNumber;
    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String description;
}
