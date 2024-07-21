package com.adkp.fuexchange.repository;

import com.adkp.fuexchange.pojo.Category;
import com.adkp.fuexchange.pojo.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDetailRepository  extends JpaRepository<ProductDetail,Integer> {


}
