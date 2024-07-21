package com.adkp.fuexchange.response;

import com.adkp.fuexchange.pojo.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisteredStudentInformationResponse {

    private int registeredStudentId;
    private Roles roleId;
    private String deliveryAddress;

    private String firstName;

    private String lastName;

    private String identityCard;

    private String address;

    private String phoneNumber;

    private String gender;

    private LocalDate dob;

    private boolean active;

    private List<RegisteredStudentInformationResponse> registeredStudentInformationResponseList;
}
