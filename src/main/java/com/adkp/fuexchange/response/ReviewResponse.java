package com.adkp.fuexchange.response;

import com.adkp.fuexchange.dto.ReviewDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReviewResponse {

    private Integer totalReview;

    private Double totalRating;

    private List<ReviewDTO> reviews;
}
