package com.adkp.fuexchange.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class StaffDTO {

    private int staffId;
    private RoleDTO role;
    private String firstName;
    private String lastName;
    private String gender;
    private String identityCard;
    private String phoneNumber;
    private LocalDate dob;
    private boolean active;
}
