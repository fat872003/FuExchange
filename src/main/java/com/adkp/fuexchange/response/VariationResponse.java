package com.adkp.fuexchange.response;

import com.adkp.fuexchange.pojo.VariationDetail;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VariationResponse {

    private int variationId;

    private String variationName;

    private VariationDetail variationDetail;

}
