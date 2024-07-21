package com.adkp.fuexchange.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderStatusDTO {
    int orderStatusId;
    String orderStatusName;
}
