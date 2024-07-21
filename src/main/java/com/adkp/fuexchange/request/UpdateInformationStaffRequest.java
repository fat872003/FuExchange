package com.adkp.fuexchange.request;

import lombok.Data;

import java.time.LocalDate;
@Data
public class UpdateInformationStaffRequest {
    private int staffId;

    private String firstName;

    private String lastName;

    private String gender;

    private String identityCard;

    private String address;

    private String phoneNumber;

    private LocalDate dob;
}
