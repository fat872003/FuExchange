package com.adkp.fuexchange.request;

import com.adkp.fuexchange.pojo.Category;
import com.adkp.fuexchange.pojo.Seller;
import com.adkp.fuexchange.pojo.Variation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class RegisterProductRequest {


    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private String productName;

    private String productDescription;



    private String studentId;
    @Min(value = 1, message = "Vui lòng nhập đầy đủ thông tin!")
    private int categoryId;
    @NotNull(message = "Vui lòng nhập đầy đủ thông tin!")
    private double price;

    private boolean productStatus;




    List<VariationRequest> variationList;

    List<ProductImageRequest> productImageRequestsList;

}
