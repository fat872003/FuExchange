package com.adkp.fuexchange.repository;

import com.adkp.fuexchange.pojo.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersStatusRepository extends JpaRepository<OrderStatus, Integer> {
}
