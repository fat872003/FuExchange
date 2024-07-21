package com.adkp.fuexchange.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportProductTypeDTO {

    private int reportProductTypeId;

    private String reportProductTypeName;

    private String description;

}
