package com.adkp.fuexchange.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewDTO {
    int review;

    int postProductId;

    int orderId;

    int rating;

    String description;

    LocalDateTime createTime;

}
