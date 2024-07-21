package com.adkp.fuexchange.dto;

import com.adkp.fuexchange.pojo.Payment;
import com.adkp.fuexchange.pojo.TransactionsStatus;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TransactionsDTO {

    private int transactionsId;

    private Payment payment;

    private TransactionsStatus transactionsStatus;

    private double totalPrice;

    private LocalDateTime createTime;

    private LocalDateTime completeTime;
}
