package com.adkp.fuexchange.response;


import com.adkp.fuexchange.dto.ProductDTO;
import com.adkp.fuexchange.pojo.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
    private ResponseObject<Object> responseObject;

    private MetaResponse meta;

    private List<ProductDTO> data;

    private ProductDTO product;

    private List<VariationResponse> variation;

    private Category categoryId;

    private double price;

    private ProductDetailResponse productDetailResponse;


}
