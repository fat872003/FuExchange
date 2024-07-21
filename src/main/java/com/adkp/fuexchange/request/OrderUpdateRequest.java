package com.adkp.fuexchange.request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateRequest {
    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int orderId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int orderStatusId;

}
