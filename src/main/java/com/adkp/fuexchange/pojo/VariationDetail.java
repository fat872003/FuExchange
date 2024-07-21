package com.adkp.fuexchange.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor(force = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "VariationDetail")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class VariationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int variationDetailId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "variationId", referencedColumnName = "variationId")
    private Variation variationId;

    private String description;

    @OneToMany(mappedBy = "variationDetailId", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonBackReference
    private List<CartPost> cartPostId;

    @OneToMany(mappedBy = "variationDetailId", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonBackReference
    private List<OrderPostProduct> orderPostProductId;

    public VariationDetail(Variation variationId, String description) {
        this.variationId = variationId;
        this.description = description;
    }
}
