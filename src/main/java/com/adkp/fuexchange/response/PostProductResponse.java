package com.adkp.fuexchange.response;

import com.adkp.fuexchange.dto.PostStatusDTO;
import com.adkp.fuexchange.pojo.Campus;
import com.adkp.fuexchange.pojo.PostStatus;
import com.adkp.fuexchange.pojo.PostType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PostProductResponse {

    private int postProductId;

    private PostType postTypeId;

    private Campus campusId;

    private PostStatus postStatusId;

    private PostStatusDTO postStatusDTO;

    private int quantity;

    private LocalDateTime createDate;

    private String content;

    private ProductResponse productResponse;

    private List<PostProductResponse> productResponseList;

    private int sellerId;

    private String productName;

    private String imageUrlProduct;

    private String firstVariation;

    private String secondVariation;

    private double priceBought;

}
