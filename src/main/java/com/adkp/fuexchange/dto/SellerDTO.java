package com.adkp.fuexchange.dto;

import lombok.Data;

@Data
public class SellerDTO {

    private int sellerId;

    private StudentDTO student;

    private String bankingName;

    private String bankingNumber;

    private int active;

}
