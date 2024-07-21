package com.adkp.fuexchange.response;

import com.adkp.fuexchange.pojo.Roles;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StaffInforResponse {

    private int staffID;
    private Roles roleId;

    private String firstName;
    private String lastName;




    private String gender;
    private String identityCard;

    private String address;
    private String phoneNumber;

    private LocalDate dob;

    private boolean active;

    public StaffInforResponse(int staffID, Roles roleId, String firstName, String lastName, String gender, String identityCard, String address, String phoneNumber, LocalDate dob, boolean active) {
        this.staffID = staffID;
        this.roleId = roleId;
        this.firstName = firstName;
        this.lastName = lastName;

        this.gender = gender;
        this.identityCard = identityCard;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dob = dob;

        this.active = active;

    }
}
