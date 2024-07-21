package com.adkp.fuexchange.repository;

import com.adkp.fuexchange.pojo.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
