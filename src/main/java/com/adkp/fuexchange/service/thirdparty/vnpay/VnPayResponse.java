package com.adkp.fuexchange.service.thirdparty.vnpay;

import com.adkp.fuexchange.request.OrdersRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VnPayResponse {
    private int status;

    private String message;

    private String content;

    private String paymentUrl;

    private OrdersRequest ordersRequest;
}
