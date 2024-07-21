package com.adkp.fuexchange.service;

import com.adkp.fuexchange.dto.StudentDTO;
import com.adkp.fuexchange.pojo.Student;
import com.adkp.fuexchange.request.StudentRequest;

import java.util.List;

public interface StudentService {
    Student viewStudentById(String studentId);

    Student createStudent(StudentRequest studentRequest);

    Student updateStudentByStudentId(StudentRequest studentRequest);

    void deleteStudentByStudentId(String studentId);

    List<StudentDTO> viewAllStudent();
}
