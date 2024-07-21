package com.adkp.fuexchange.repository;

import com.adkp.fuexchange.pojo.OrderPostProduct;
import com.adkp.fuexchange.pojo.Orders;
import com.adkp.fuexchange.pojo.PostProduct;
import com.adkp.fuexchange.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderPostProductRepository extends JpaRepository<OrderPostProduct, Integer> {

    @Query("SELECT odpprd.orderId From OrderPostProduct odpprd WHERE " +
            "odpprd.postProductId.productId.sellerId.sellerId = :sellerId")
    List<Orders> getOrdersBySellerId(@Param("sellerId") Integer sellerId);

    @Query("SELECT odpprd FROM OrderPostProduct odpprd " +
            "WHERE odpprd.postProductId.productId.sellerId.sellerId = :sellerId " +
            "AND odpprd.orderId.orderId = :orderId " +
            "ORDER BY odpprd.orderId.createDate DESC")
    List<OrderPostProduct> getOrdersDetailBySellerIdAndOrderId(
            @Param("sellerId") Integer sellerId, @Param("orderId") Integer orderId
    );

    @Query("SELECT odpprd FROM OrderPostProduct odpprd " +
            "WHERE odpprd.orderId.registeredStudentId.registeredStudentId = :registeredStudentId " +
            "ORDER BY odpprd.orderId.createDate DESC, sttOrder ASC")
    List<OrderPostProduct> getOrdersDetailByRegisteredStudentId(
            @Param("registeredStudentId") Integer registeredStudentId
    );

    @Query("SELECT odpprd From OrderPostProduct odpprd WHERE " +
            "odpprd.postProductId.productId IN :product " +
            "ORDER BY odpprd.postProductId.postProductId")
    List<OrderPostProduct> getAllOrdersByProduct(@Param("product") List<Product> products);

    @Query("SELECT COUNT(odpprd) From OrderPostProduct odpprd WHERE " +
            "odpprd.postProductId.postProductId = :postProductId " +
            "AND orderId.orderStatusId.orderStatusId IN (1, 2, 3)")
    long checkOrderInDeletePost(
            @Param("postProductId") Integer postProductId
    );
}
