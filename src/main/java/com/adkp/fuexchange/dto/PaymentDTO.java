package com.adkp.fuexchange.dto;

import com.adkp.fuexchange.pojo.Orders;
import com.adkp.fuexchange.pojo.PaymentMethod;
import com.adkp.fuexchange.pojo.Transactions;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PaymentDTO {
    private int paymentId;

    private List<Orders> order;

    private PaymentMethod paymentMethod;

    private boolean paymentStatus;

    private LocalDateTime createTime;

    private Transactions transaction;
}
