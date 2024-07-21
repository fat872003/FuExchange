package com.adkp.fuexchange.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VariationDetailDTO {

    private VariationDTO variation;

    private int variationDetailId;

    private String description;
}
