package com.adkp.fuexchange.dto;

import com.adkp.fuexchange.pojo.PostProduct;
import com.adkp.fuexchange.pojo.RegisteredStudent;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class WishListDTO {

    private int wishListId;

    private PostProductDTO postProduct;

    private RegisteredStudentDTO registeredStudent;

    private LocalDateTime createTime;

    private int quantity;

    private boolean active;
}
