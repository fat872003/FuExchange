package com.adkp.fuexchange.service;

import com.adkp.fuexchange.dto.ProductDTO;
import com.adkp.fuexchange.request.RegisterProductRequest;
import com.adkp.fuexchange.request.UpdateInformationProductRequest;
import com.adkp.fuexchange.response.ProductResponse;
import com.adkp.fuexchange.response.ResponseObject;

import java.util.List;


public interface ProductService {

    ResponseObject<Object> topProductByUserIdAndName(String studentId, String productName, int current);

    ResponseObject<Object> getProductByProductID(int productID);

    ResponseObject<Object> createProduct(RegisterProductRequest registerProductRequest);

    ProductDTO updateProductInformation(UpdateInformationProductRequest updateInformationProductRequest);

    ProductResponse getProductByVariationDetailId(List<Integer> variationDetailId);

    ProductDTO deleteProduct(Integer productId);
}
