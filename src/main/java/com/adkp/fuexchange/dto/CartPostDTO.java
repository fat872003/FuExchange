package com.adkp.fuexchange.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartPostDTO {

    int sttPostInCart;

    CartDTO cart;

    PostProductDTO postProduct;

    VariationDetailDTO variationDetail;

    int quantity;
}
