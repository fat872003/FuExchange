package com.adkp.fuexchange.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "PostStatus")
public class PostStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postStatusId;

    private String postStatusName;
    
    public PostStatus(String postStatusName) {
        this.postStatusName = postStatusName;
    }
}
