package com.adkp.fuexchange.repository;

import com.adkp.fuexchange.pojo.VariationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VariationDetailRepository extends JpaRepository<VariationDetail, Integer> {

    @Query("Select vratdt From VariationDetail vratdt Where vratdt.variationDetailId In :variationId")
    List<VariationDetail> getVariationDetailByVariationId(@Param("variationId") List<Integer> variationId);

//    VariationDetail getVariationDetailById()
}
