package com.adkp.fuexchange.dto;

import com.adkp.fuexchange.pojo.RegisteredStudent;
import com.adkp.fuexchange.pojo.ReportSellerType;
import com.adkp.fuexchange.pojo.ReportStatus;
import com.adkp.fuexchange.pojo.Seller;
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
public class ReportSellerDTO {

    private int reportSellerId;

    private int buyerId;

    private String buyerName;

    private int sellerId;

    private String sellerName;

    private ReportSellerTypeDTO reportSellerType;

    private ReportStatusDTO reportStatus;

    private LocalDateTime createTime;

    private String content;
}
