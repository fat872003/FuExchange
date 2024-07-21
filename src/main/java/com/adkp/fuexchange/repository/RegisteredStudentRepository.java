package com.adkp.fuexchange.repository;

import com.adkp.fuexchange.pojo.RegisteredStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegisteredStudentRepository extends JpaRepository<RegisteredStudent, Integer> {

    @Query("Select rgtstd From RegisteredStudent rgtstd " +
            "Where rgtstd.studentId.studentId = :studentId")
    RegisteredStudent findRegisteredStudentByStudentId(@Param("studentId") String studentId);

    @Query(value = "Select rgtstd.* From RegisteredStudent rgtstd " +
            "JOIN Student std ON std.studentId = rgtstd.studentId " +
            "WHERE CONCAT(std.firstName, ' ', std.lastName) " +
            "LIKE CONCAT('%', :studentName, '%')",
            nativeQuery = true
    )
    List<RegisteredStudent> filterRegisteredStudent(
            @Param("studentName") String studentName
    );
}
