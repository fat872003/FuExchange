package com.adkp.fuexchange.dto;

import lombok.Data;

@Data
public class RegisteredStudentDTO {

    private int registeredStudentId;

    private StudentDTO student;

    private RoleDTO role;

    private boolean active;

    private String deliveryAddress;
}
