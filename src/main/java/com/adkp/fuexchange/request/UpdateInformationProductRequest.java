package com.adkp.fuexchange.request;

import com.adkp.fuexchange.dto.VariationDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class UpdateInformationProductRequest {

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private Integer productId;

    @NotEmpty(message = "Vui lòng nhập đầy đủ thông tin!")
    private String productName;

    @NotEmpty(message = "Vui lòng nhập đầy đủ thông tin!")
    private String productDescription;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private Integer categoryId;

    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private double price;

    List<UpdateVariationRequest> variation;
}
