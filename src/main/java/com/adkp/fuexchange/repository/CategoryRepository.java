package com.adkp.fuexchange.repository;

import com.adkp.fuexchange.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query("SELECT ctgr FROM Category ctgr")
    List<Category> findAllCategoryProductType();
}
