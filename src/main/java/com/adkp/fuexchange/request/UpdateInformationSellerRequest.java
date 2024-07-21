package com.adkp.fuexchange.request;

import lombok.Data;

@Data
public class UpdateInformationSellerRequest {

    private Integer sellerId;

    private String bankingNumber;

    private String bankingName;

}
