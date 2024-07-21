package com.adkp.fuexchange.response;

import com.adkp.fuexchange.dto.OrdersDTO;
import com.adkp.fuexchange.dto.PostProductDTO;
import com.adkp.fuexchange.dto.VariationDetailDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class OrderDetailResponse {

    private OrdersDTO order;

    private PostProductDTO postProduct;

    private List<VariationDetailDTO> variationDetail;

    private List<PostProductResponse> postProductInOrder;

    private double totalPrice;
}
