package com.adkp.fuexchange.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(force = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "postProductId", referencedColumnName = "postProductId")
    private PostProduct postProductId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    private Orders orderId;

    private int ratingNumber;

    private String description;

    private LocalDateTime createTime;

    public Review(PostProduct postProductId, Orders orderId, int ratingNumber, String description, LocalDateTime createTime) {
        this.postProductId = postProductId;
        this.orderId = orderId;
        this.ratingNumber = ratingNumber;
        this.description = description;
        this.createTime = createTime;
    }
}
