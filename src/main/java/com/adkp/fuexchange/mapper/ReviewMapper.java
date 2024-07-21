package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.ReviewDTO;
import com.adkp.fuexchange.pojo.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {OrdersMapper.class, PostProductMapper.class}
)
public interface ReviewMapper {

    @Mapping(source = "reviewId", target = "review")
    @Mapping(source = "postProductId.postProductId", target = "postProductId")
    @Mapping(source = "orderId.orderId", target = "orderId")
    @Mapping(source = "ratingNumber", target = "rating")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "createTime", target = "createTime")
    ReviewDTO toReviewDTO(Review review);

    List<ReviewDTO> toReviewDTOList(List<Review> reviewList);

}
