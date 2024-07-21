package com.adkp.fuexchange.controller.student;

import com.adkp.fuexchange.pojo.Student;
import com.adkp.fuexchange.request.StudentRequest;
import com.adkp.fuexchange.response.ResponseObject;
import com.adkp.fuexchange.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@Tag(name = "Student")
@Validated
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(summary = "Get all student")
    @GetMapping("/view-all")
    public ResponseObject<Object> getAllStudent() {
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .data(
                        studentService.viewAllStudent()
                )
                .content("Xem thông tin thành công!")
                .build();
    }

    @Operation(summary = "View student by studentId")
    @GetMapping("/view-student")
    public ResponseObject<Object> getStudentById(@RequestParam("studentId") String studentId) {
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .data(
                        studentService.viewStudentById(studentId)
                )
                .content("Xem thông tin thành công!")
                .build();
    }

    @Operation(summary = "Create student")
    @PostMapping("/create-student")
    public ResponseObject<Object> createStudent(@Valid @RequestBody StudentRequest studentRequest) {
        Student createdStudent = studentService.createStudent(studentRequest);
        if(createdStudent == null){
            return ResponseObject.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(HttpStatus.BAD_REQUEST.name())
                    .content("Mã sinh viên hoặc số identify đã tồn tại!")
                    .build();
        }
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Tạo thành công")
                .data(studentRequest)
                .build();
    }

    @Operation(summary = "Update student")
    @PutMapping("/{studentId}/update-student")
    public ResponseObject<Object> updateStudentById(
            @Valid @RequestBody StudentRequest studentRequest
    ) {
        Student updatedStudent = studentService.updateStudentByStudentId(studentRequest);
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Cập nhật thành công")
                .data(updatedStudent)
                .build();
    }

    @Operation(summary = "Delete student")
    @DeleteMapping("/{studentId}")
    public ResponseObject<Object> deleteStudentById(
            @PathVariable("studentId") String studentId
    ) {
        studentService.deleteStudentByStudentId(studentId);
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xóa thành công")
                .build();
    }
}
