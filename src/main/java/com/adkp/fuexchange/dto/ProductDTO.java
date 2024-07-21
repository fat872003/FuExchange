package com.adkp.fuexchange.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonIgnoreProperties({"productDetailId"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    private int productId;

    private SellerDTO seller;

    private ProductDetailDTO detail;

    private List<ProductImageDTO> image;

    private CategoryDTO category;

    private List<VariationDTO> variation;

    private String price;

    private boolean productStatus;
}
