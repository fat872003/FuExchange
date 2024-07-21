package com.adkp.fuexchange.response;

import com.adkp.fuexchange.pojo.Product;
import com.adkp.fuexchange.pojo.Variation;
import lombok.Data;

@Data
public class RegisterVariationDetailResponse {

    private String description;

    public RegisterVariationDetailResponse( String description) {

        this.description = description;
    }
}
