package com.adkp.fuexchange.repository;

import com.adkp.fuexchange.pojo.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {

    @Query("Select tst From Transactions tst Where tst.paymentId.paymentId = :paymentId")
    Transactions getTransactionByPaymentId(@Param("paymentId") int paymentId);
}
