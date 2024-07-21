package com.adkp.fuexchange.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Builder
public class OrdersDTO {
    int orderId;

    int registeredStudent;

    OrderStatusDTO orderStatus;

    LocalDateTime createDate;

    LocalDateTime completeDate;

    String description;

    int paymentId;

    long totalPrice;
}
