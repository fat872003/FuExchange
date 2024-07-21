package com.adkp.fuexchange.pojo;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartPostEmbeddable implements Serializable {
    private int cartId;

    private int postProductId;

    private int variationDetailId;
}
