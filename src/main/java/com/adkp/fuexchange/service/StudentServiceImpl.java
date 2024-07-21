package com.adkp.fuexchange.service;

import com.adkp.fuexchange.dto.StudentDTO;
import com.adkp.fuexchange.mapper.StudentMapper;
import com.adkp.fuexchange.pojo.Student;
import com.adkp.fuexchange.repository.StudentRepository;
import com.adkp.fuexchange.request.StudentRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public Student viewStudentById(String studentId) {
        return studentRepository.getReferenceById(studentId);
    }

    @Override
    @Transactional
    public Student createStudent(StudentRequest studentRequest) {
        if (
                studentRepository.existsById(studentRequest.getStudentId()) &&
                        studentRepository.getReferenceById(studentRequest.getStudentId()).getIdentityCard().equals(studentRequest.getIdentityCard())
        ) {
            return null;
        }
        return studentRepository.save(
                Student.builder()
                        .studentId(studentRequest.getStudentId())
                        .firstName(studentRequest.getFirstName())
                        .lastName(studentRequest.getLastName())
                        .identityCard(studentRequest.getIdentityCard())
                        .address(studentRequest.getAddress())
                        .phoneNumber(studentRequest.getPhoneNumber())
                        .gender(studentRequest.getGender())
                        .dob(studentRequest.getDob())
                        .build()
        );
    }

    @Transactional
    @Override
    public Student updateStudentByStudentId(StudentRequest studentRequest) {
        Student studentToUpdate = studentRepository.getReferenceById(studentRequest.getStudentId());

        studentToUpdate.setFirstName(studentRequest.getFirstName());
        studentToUpdate.setLastName(studentRequest.getLastName());
        studentToUpdate.setIdentityCard(studentRequest.getIdentityCard());
        studentToUpdate.setAddress(studentRequest.getAddress());
        studentToUpdate.setPhoneNumber(studentRequest.getPhoneNumber());
        studentToUpdate.setGender(studentRequest.getGender());
        studentToUpdate.setDob(studentRequest.getDob());

        return studentRepository.save(studentToUpdate);
    }

    @Transactional
    @Override
    public void deleteStudentByStudentId(String studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public List<StudentDTO> viewAllStudent() {
        return studentMapper.toStudentDtoList(studentRepository.findAll());
    }
}
