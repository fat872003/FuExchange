package com.adkp.fuexchange.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "PostProduct")
@ToString
public class PostProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postProductId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private Product productId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
    @JoinColumn(name = "postTypeId", referencedColumnName = "postTypeId")
    private PostType postTypeId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
    @JoinColumn(name = "campusId", referencedColumnName = "campusId")
    private Campus campusId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
    @JoinColumn(name = "postStatusId", referencedColumnName = "postStatusId")
    private PostStatus postStatusId;

    private int quantity;

    private LocalDateTime createDate;

    private String content;

    @OneToMany(mappedBy = "postProductId", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference
    private List<CartPost> cartPostId;

    public PostProduct(Product productId, PostType postTypeId, Campus campusId, PostStatus postStatusId, int quantity, LocalDateTime createDate) {
        this.productId = productId;
        this.postTypeId = postTypeId;
        this.campusId = campusId;
        this.postStatusId = postStatusId;
        this.quantity = quantity;
        this.createDate = createDate;
    }
}
