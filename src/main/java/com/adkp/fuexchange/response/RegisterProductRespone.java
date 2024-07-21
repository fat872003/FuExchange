package com.adkp.fuexchange.response;

import com.adkp.fuexchange.pojo.*;
import lombok.Data;

import java.util.List;

@Data

public class RegisterProductRespone {
    private int productId;
    private int sellerId;
    private Category categoryId;
    private double price;
    private boolean productStatus;
    private List<RegisterVariationResponse> variationList;

    private ProductDetail productDetail;

    public RegisterProductRespone(int productId, int sellerId,
                                  Category categoryId, double price,
                                  boolean productStatus, List<RegisterVariationResponse> variationList,
                                  ProductDetail productDetail) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.categoryId = categoryId;
        this.price = price;
        this.productStatus = productStatus;
        this.variationList = variationList;
        this.productDetail = productDetail;
    }
}
