package com.adkp.fuexchange.repository;

import com.adkp.fuexchange.pojo.ReportPostProduct;
import com.adkp.fuexchange.pojo.ReportSeller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportSellerRepository extends JpaRepository<ReportSeller, Integer> {

    @Query(value = "SELECT rpsl.* FROM ReportSeller rpsl " +
            "JOIN Seller sl ON rpsl.sellerId = sl.sellerId " +
            "JOIN RegisteredStudent rgtstd ON sl.registeredStudentId = rgtstd.registeredStudentId " +
            "JOIN Student std ON rgtstd.studentId = std.studentId " +
            "WHERE CONCAT(std.firstName, ' ' , std.lastName) " +
            "LIKE CONCAT('%', :sellerName, '%') " +
            "AND rpsl.reportSellerTypeId LIKE CONCAT('%', :reportSellerTypeId, '%') " +
            "AND rpsl.reportStatusId LIKE CONCAT('%', :reportStatusId, '%') " +
            "ORDER BY rpsl.createTime DESC",
            nativeQuery = true)
    List<ReportSeller> filterReportSeller(
            @Param("sellerName") String sellerName,
            @Param("reportSellerTypeId") String reportSellerTypeId,
            @Param("reportStatusId") String reportStatusId
    );

    @Query("SELECT rpsl FROM ReportSeller rpsl " +
            "WHERE rpsl.buyerId.registeredStudentId = :buyerId " +
            "AND rpsl.sellerId.sellerId = :sellerId")
    ReportSeller checkReportedOfStudentInReportSeller(
            @Param("buyerId") Integer buyerId,
            @Param("sellerId") Integer sellerId
    );

    @Query("SELECT rpsl FROM ReportSeller rpsl " +
            "WHERE rpsl.sellerId.sellerId = :sellerId")
    List<ReportSeller> getReportBySellerId(
            @Param("sellerId") Integer sellerId
    );

    @Modifying
    @Query("UPDATE ReportSeller rpsl " +
            "SET rpsl.reportStatusId.reportStatusId = :reportStatusId " +
            "WHERE rpsl.sellerId.sellerId = :sellerId")
    void updateStatusSeller(
            @Param("sellerId") Integer sellerId,
            @Param("reportStatusId") Integer reportStatusId
    );
}
