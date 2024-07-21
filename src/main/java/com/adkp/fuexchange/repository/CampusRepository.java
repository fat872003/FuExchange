package com.adkp.fuexchange.repository;

import com.adkp.fuexchange.pojo.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CampusRepository extends JpaRepository<Campus, Integer> {
    @Query("SELECT c FROM Campus c")
    List<Campus> findAllCampus();
}
