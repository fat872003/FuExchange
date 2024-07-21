package com.adkp.fuexchange.service;

import com.adkp.fuexchange.dto.OrdersDTO;
import com.adkp.fuexchange.request.OrderUpdateRequest;

public interface OrderService {

    OrdersDTO updateOrder(OrderUpdateRequest orderUpdateRequest);

    OrdersDTO deleteOrder(Integer orderId);
}
