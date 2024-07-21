package com.adkp.fuexchange.response;

import com.adkp.fuexchange.pojo.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartPostResponse {
    private CartPostEmbeddable cartPostId;
    private ProductDetail productDetailId; //prdDetail
    private Seller sellerId;
    private Category categoryId;
    private List<Variation> variationId;

}
