package com.adkp.fuexchange.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "PostType")
public class PostType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postTypeId;

    private String postTypeName;

    public PostType(String postTypeName) {
        this.postTypeName = postTypeName;
    }
}
