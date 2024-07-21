package com.adkp.fuexchange.repository;
import com.adkp.fuexchange.pojo.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
@Query("SELECT prdImg FROM ProductImage prdImg WHERE prdImg.productDetailId = :productDetailId")
List<ProductImage> getProductImgByProductDetailID(@Param("productDetailId") int productDetailId);

}
