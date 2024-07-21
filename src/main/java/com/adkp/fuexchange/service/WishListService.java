package com.adkp.fuexchange.service;

import com.adkp.fuexchange.request.RegisterWishListRequest;
import com.adkp.fuexchange.response.ResponseObject;

public interface WishListService {

    ResponseObject<Object> createWishList(RegisterWishListRequest registerWishListRequest);

    ResponseObject<Object> viewWishListByPostProductID( int postProductID);

    ResponseObject<Object> UpdateQuantity(int wishlistID,int quantity);

    ResponseObject<Object> UpdateActive(int wishListID,int active);

    void deleteWishList(int wishListID);
}
