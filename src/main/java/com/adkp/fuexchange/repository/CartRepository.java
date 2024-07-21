package com.adkp.fuexchange.repository;

import com.adkp.fuexchange.pojo.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query("SELECT c FROM Cart c WHERE c.registeredStudentId.registeredStudentId = :registeredStudentId")
    Cart getCartByRegisteredStudentId(@Param("registeredStudentId") int registeredStudentId);
}
