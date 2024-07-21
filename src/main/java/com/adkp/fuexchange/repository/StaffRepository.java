package com.adkp.fuexchange.repository;


import com.adkp.fuexchange.pojo.Staff;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

    @Query(value = "SELECT stf.* FROM Staff stf WHERE stf.identityCard LIKE CONCAT('%', :identityCard, '%') And " +
            "stf.roleId=4",
            nativeQuery = true)
    List<Staff> topStaffs(Pageable pageable, @Param("identityCard") String identityCard);

    @Query("Select stf From Staff stf " +
            "Where stf.phoneNumber = :phoneNumber")
    Staff findStaffByNumberPhone(@Param("phoneNumber") String phoneNumber);
    @Query("Select stf From Staff stf " +
            "Where stf.identityCard = :identityCard AND stf.staffId != :staffId")
    Staff checkAvailableStaffByIdentify(@Param("identityCard") String identityCard,@Param("staffId") int staffId);

    @Query("Select stf From Staff stf " +
            "Where stf.phoneNumber = :phoneNumber AND stf.staffId != :staffId")
    Staff checkAvailableStaffByNumberPhone(@Param("phoneNumber") String phoneNumber,@Param("staffId") int staffId);

}
