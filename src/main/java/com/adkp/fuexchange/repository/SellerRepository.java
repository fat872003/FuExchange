package com.adkp.fuexchange.repository;

import com.adkp.fuexchange.pojo.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Integer> {

    @Query("Select sl From Seller sl Where sl.registeredStudentId.studentId.studentId = :studentId")
    Seller getInformationSellerByStudentId(@Param("studentId") String studentId);

    @Query("Select sl From Seller sl Where sl.isActive = 2")
    List<Seller> viewRegisterToSellerRequest();
}
