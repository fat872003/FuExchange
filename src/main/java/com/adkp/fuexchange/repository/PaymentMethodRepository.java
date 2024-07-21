package com.adkp.fuexchange.repository;

import com.adkp.fuexchange.pojo.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
}
