package com.adkp.fuexchange.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDetailDTO {

    private int productDetailId;

    private String productName;

    private String description;

    private List<ProductImageDTO> productImage;
}
