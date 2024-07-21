package com.adkp.fuexchange.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderPostProductDTO {

    private int sttOrder;

    private OrdersDTO order;

    private PostProductDTO postProduct;

    private VariationDetailDTO variationDetail;

    private int quantity;

    private long priceBought;

}
