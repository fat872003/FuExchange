package com.adkp.fuexchange.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor(force = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name = "paymentMethodId", referencedColumnName = "paymentMethodId")
    private PaymentMethod paymentMethodId;

    private boolean paymentStatus;

    private LocalDateTime createTime;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "paymentId")
    @JsonBackReference
    private Transactions transactionId;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE}, mappedBy = "orderId")
    @JsonBackReference
    private List<Orders> orderId;

    public Payment(PaymentMethod paymentMethodId, boolean paymentStatus, LocalDateTime createTime) {
        this.paymentMethodId = paymentMethodId;
        this.paymentStatus = paymentStatus;
        this.createTime = createTime;
    }
}
