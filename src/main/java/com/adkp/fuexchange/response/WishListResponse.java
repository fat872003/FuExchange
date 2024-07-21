package com.adkp.fuexchange.response;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WishListResponse {

    private int wishListId;

    private int registeredStudentId;

    private PostProductResponse postProductResponse;
    private int quantity;
    private List<WishListResponse> wishListResponesList;
    private LocalDateTime createTime;
    private boolean isActive;
}
