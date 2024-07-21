package com.adkp.fuexchange.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Builder
public class PostProductDTO {

    private int postProductId;

    private int sellerId;

    private ProductDTO product;

    private PostTypeDTO postType;

    private CampusDTO campus;

    private PostStatusDTO postStatus;

    private int quantity;

    private String createDate;

    private String content;

    private double priceBought;

    private int totalReview;

    private Double totalRating;
}
