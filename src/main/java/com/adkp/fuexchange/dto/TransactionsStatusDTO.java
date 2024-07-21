package com.adkp.fuexchange.dto;

import com.adkp.fuexchange.pojo.Transactions;
import lombok.Data;

import java.util.List;

@Data
public class TransactionsStatusDTO {
    private int transactionsStatusId;

    private String transactionsStatusName;

    private List<Transactions> transactionId;
}
