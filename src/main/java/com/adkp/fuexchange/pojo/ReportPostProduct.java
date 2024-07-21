package com.adkp.fuexchange.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(force = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Builder
@AllArgsConstructor
@Table(name = "ReportPostProduct")
public class ReportPostProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportPostProductId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "buyerId", referencedColumnName = "registeredStudentId")
    private RegisteredStudent buyerId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "postProductId", referencedColumnName = "postProductId")
    private PostProduct postProductId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "reportProductTypeId", referencedColumnName = "reportProductTypeId")
    private ReportProductType reportProductTypeId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "reportStatusId", referencedColumnName = "reportStatusId")
    private ReportStatus reportStatusId;

    private LocalDateTime createTime;

    private String content;

    public ReportPostProduct(RegisteredStudent buyerId, PostProduct postProductId, ReportProductType reportProductTypeId, ReportStatus reportStatusId, LocalDateTime createTime, String content) {
        this.buyerId = buyerId;
        this.postProductId = postProductId;
        this.reportProductTypeId = reportProductTypeId;
        this.reportStatusId = reportStatusId;
        this.createTime = createTime;
        this.content = content;
    }
}
