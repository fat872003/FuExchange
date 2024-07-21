package com.adkp.fuexchange.service;

import com.adkp.fuexchange.dto.ReviewDTO;
import com.adkp.fuexchange.request.RegisterReviewRequest;
import com.adkp.fuexchange.request.UpdateInformationReviewRequest;
import com.adkp.fuexchange.response.ResponseObject;
import com.adkp.fuexchange.response.ReviewResponse;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> getReviewByOrderId(Integer orderId);

    ReviewResponse getReviewByPostProduct(Integer postProductId);
    ResponseObject<Object>createReview (RegisterReviewRequest registerReviewRequest );

    ResponseObject<Object>updateReview(int reviewID,UpdateInformationReviewRequest updateInformationReviewRequest);
    void deleteReview(int reviewID);
}
