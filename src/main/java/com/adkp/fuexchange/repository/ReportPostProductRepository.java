package com.adkp.fuexchange.repository;

import com.adkp.fuexchange.pojo.ReportPostProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportPostProductRepository extends JpaRepository<ReportPostProduct, Integer> {

    @Query(value = "SELECT rppprd.* FROM ReportPostProduct rppprd " +
            "JOIN PostProduct pprd ON pprd.postProductId = rppprd.postProductId " +
            "JOIN Product prd ON prd.productId = pprd.productId " +
            "JOIN ProductDetail prddt ON prddt.productDetailId = prd.productDetailId " +
            "WHERE prddt.productName LIKE CONCAT('%', :productName, '%') " +
            "AND rppprd.reportProductTypeId LIKE CONCAT('%', :reportProductTypeId, '%') " +
            "AND rppprd.reportStatusId LIKE CONCAT('%', :reportStatusId, '%') " +
            "ORDER BY rppprd.createTime DESC",
            nativeQuery = true
    )
    List<ReportPostProduct> filterReportPostProduct(
            @Param("productName") String productName,
            @Param("reportProductTypeId") String reportProductTypeId,
            @Param("reportStatusId") String reportStatusId
    );

    @Query("SELECT rppprd FROM ReportPostProduct rppprd " +
            "WHERE rppprd.buyerId.registeredStudentId = :buyerId " +
            "AND rppprd.postProductId.postProductId = :postProductId")
    ReportPostProduct checkReportedOfStudentInReportPostProduct(
            @Param("buyerId") Integer buyerId,
            @Param("postProductId") Integer postProductId
    );

    @Query("SELECT rppprd FROM ReportPostProduct rppprd " +
            "WHERE rppprd.postProductId.postProductId = :postProductId")
    List<ReportPostProduct> getReportByPostId(
            @Param("postProductId") Integer postProductId
    );

    @Modifying
    @Query("UPDATE ReportPostProduct rppprd " +
            "SET rppprd.reportStatusId.reportStatusId = :reportStatusId " +
            "WHERE rppprd.postProductId.postProductId = :postProductId")
    void updateStatusPostProduct(
            @Param("postProductId") Integer postProductId,
            @Param("reportStatusId") Integer reportStatusId
    );
}
