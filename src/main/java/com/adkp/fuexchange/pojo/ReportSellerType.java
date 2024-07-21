package com.adkp.fuexchange.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "ReportSellerType")
public class ReportSellerType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportSellerTypeId;

    private String reportTypeName;

    public ReportSellerType(String reportTypeName) {
        this.reportTypeName = reportTypeName;
    }
}
