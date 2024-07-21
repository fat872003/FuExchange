package com.adkp.fuexchange.response;

import lombok.Data;

import java.util.List;

@Data
public class RegisterVariationResponse {
    private int variationId;
    private String variationName;
    private List<RegisterVariationDetailResponse> registerVariationDetailResponseList;
    public RegisterVariationResponse(int variationId, String variationName,List<RegisterVariationDetailResponse> registerVariationDetailResponseList) {
        this.variationId = variationId;
        this.variationName = variationName;
        this.registerVariationDetailResponseList = registerVariationDetailResponseList;
    }
}
