package com.adkp.fuexchange.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(force = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "TransactionsStatus")
public class TransactionsStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionsStatusId;

    private String transactionsStatusName;

    @OneToMany(mappedBy = "transactionsStatusId", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonBackReference
    private List<Transactions> transactionId;

    public TransactionsStatus(String transactionsStatusName) {
        this.transactionsStatusName = transactionsStatusName;
    }
}
