package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.StudentDTO;
import com.adkp.fuexchange.pojo.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper {

    @Mapping(source = "studentId", target = "studentId")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "identityCard", target = "identityCard")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "dob", target = "dob")
    StudentDTO toStudentDTO(Student student);

    List<StudentDTO> toStudentDtoList(List<Student> studentList);

}
