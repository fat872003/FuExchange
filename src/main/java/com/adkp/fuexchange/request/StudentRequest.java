package com.adkp.fuexchange.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class StudentRequest {

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String studentId;

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String firstName;

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String lastName;

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String identityCard;

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String address;

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String phoneNumber;

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String gender;

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private LocalDate dob;
}
