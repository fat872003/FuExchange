package com.adkp.fuexchange.dto;

import com.adkp.fuexchange.pojo.PostProduct;
import com.adkp.fuexchange.pojo.RegisteredStudent;
import com.adkp.fuexchange.pojo.ReportProductType;
import com.adkp.fuexchange.pojo.ReportStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportPostProductDTO {

    private int reportPostProductId;

    private int buyerId;

    private String buyerName;

    private int postProductId;

    private String postProductName;

    private ReportProductTypeDTO reportProductType;

    private ReportStatusDTO reportStatus;

    private String content;

    private LocalDateTime createTime;

}
