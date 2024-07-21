package com.adkp.fuexchange.service;

import com.adkp.fuexchange.dto.OrdersDTO;
import com.adkp.fuexchange.dto.SellerDTO;
import com.adkp.fuexchange.request.RegisterToSellerRequest;
import com.adkp.fuexchange.request.UpdateInformationSellerRequest;
import com.adkp.fuexchange.request.UpdateStatusRequest;
import com.adkp.fuexchange.response.OrderDetailResponse;
import com.adkp.fuexchange.response.ResponseObject;

import java.util.List;

public interface SellerService {

    ResponseObject<Object> viewInformationSellerById(int sellerId);

    ResponseObject<Object> registerToSeller(RegisterToSellerRequest registerToSellerRequest);

    ResponseObject<Object> updateInformationSeller(UpdateInformationSellerRequest updateInformationSellerRequest);

    ResponseObject<Object> updateStatusSeller(UpdateStatusRequest updateStatusRequest);

    SellerDTO checkSellerByStudentID(String studentId);
    ResponseObject <Object> getInformationSellerByStudentId(String studentId);

    void deleteSellerByID(int sellerID);

    List<OrdersDTO> getOrderBySellerId(Integer sellerId);

    List<OrderDetailResponse> getOrderDetailBySellerIdAndOrderId(Integer sellerId, Integer orderId);

    List<SellerDTO> getAllSeller();
}
