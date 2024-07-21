package com.adkp.fuexchange.repository;

import com.adkp.fuexchange.pojo.CartPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartPostRepository extends JpaRepository<CartPost, Integer> {

    @Query("Select cp From CartPost cp")
    List<CartPost> findAllCartPost();

    @Query("SELECT cp FROM CartPost cp " +
            "WHERE cp.cartId.registeredStudentId.registeredStudentId = :registeredStudentId Order By cp.sttPostInCart")
    List<CartPost> getCartProductByRegisteredStudentId(@Param("registeredStudentId") Integer registeredStudentId);

    @Query("SELECT cp FROM CartPost cp " +
            "WHERE cp.cartId.cartId = :cartId AND cp.postProductId.postProductId = :postProductId AND cp.variationDetailId.variationDetailId = :variationDetailId ")
    CartPost getCartPostById(@Param("cartId") int cartId, @Param("postProductId") int postProductId, @Param("variationDetailId") int variationDetailId);

    @Query("SELECT cp FROM CartPost cp WHERE cp.cartId.cartId = :cartId " +
            "AND cp.postProductId.postProductId = :postProductId " +
            "AND cp.variationDetailId.variationDetailId IN :variationDetailId")
    List<CartPost> getCartPostByAllId(
            @Param("cartId") int cartId,
            @Param("postProductId") int postProductId,
            @Param("variationDetailId") List<Integer> variationDetailId
    );

    @Query(value = "SELECT TOP 1 cp.sttPostInCart FROM CartPost cp " +
            "WHERE cp.cartId = :cartId " +
            "ORDER BY cp.sttPostInCart DESC", nativeQuery = true)
    Integer getSttLastCart(@Param("cartId") Integer cartId);

    @Query("SELECT cp FROM CartPost cp WHERE cp.cartId.cartId = :cartId " +
            "AND cp.postProductId.postProductId IN :postProductId " +
            "AND cp.variationDetailId.variationDetailId IN :variationDetailId")
    List<CartPost> getCartPostByAllListId(
            @Param("cartId") int cartId,
            @Param("postProductId") List<Integer> postProductId,
            @Param("variationDetailId") List<Integer> variationDetailId
    );
}
