package com.adkp.fuexchange.service;

import com.adkp.fuexchange.dto.OrderPostProductDTO;
import com.adkp.fuexchange.request.OrdersRequest;

import java.util.List;

public interface PaymentService {

    List<OrderPostProductDTO> payOrders(OrdersRequest ordersRequest);

}
