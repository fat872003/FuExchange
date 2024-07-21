package com.adkp.fuexchange.repository;

import com.adkp.fuexchange.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
}
