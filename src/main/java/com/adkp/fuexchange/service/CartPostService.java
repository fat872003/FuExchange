package com.adkp.fuexchange.service;

import com.adkp.fuexchange.dto.CartPostDTO;
import com.adkp.fuexchange.request.CartRequest;
import com.adkp.fuexchange.request.UpdateCartRequest;

import java.util.List;

public interface CartPostService {
    List<CartPostDTO> viewCartPostProductByStudentId(Integer registeredStudentId);

    List<CartPostDTO> addToCart(CartRequest cartRequest);

    List<CartPostDTO> updateCart(UpdateCartRequest updateCartRequest);

    boolean removeFromCart(CartRequest cartRequest);

}
