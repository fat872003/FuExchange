package com.adkp.fuexchange.service;

import com.adkp.fuexchange.dto.OrdersDTO;
import com.adkp.fuexchange.dto.PostProductDTO;
import com.adkp.fuexchange.dto.SellerDTO;

import java.util.List;

public interface ModeratorService {

    List<SellerDTO> viewRegisterToSellerRequest();

    List<OrdersDTO> viewCreateOrderRequest();

    List<PostProductDTO> viewCreatePostProductRequest();
}
