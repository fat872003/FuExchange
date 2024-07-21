package com.adkp.fuexchange.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartRequest {

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin")
    int registeredStudentId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin")
    int postProductId;

    @NotNull(message = "Vui lòng nhập đầy đủ thông tin")
    List<Integer> variationDetailId;

    int quantity;
}
