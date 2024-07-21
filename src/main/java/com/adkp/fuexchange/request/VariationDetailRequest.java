package com.adkp.fuexchange.request;

import com.adkp.fuexchange.pojo.Variation;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VariationDetailRequest {

    private String description;

}
