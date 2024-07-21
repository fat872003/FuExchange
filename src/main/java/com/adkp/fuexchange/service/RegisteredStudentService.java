package com.adkp.fuexchange.service;

import com.adkp.fuexchange.dto.RegisteredStudentDTO;
import com.adkp.fuexchange.request.UpdatePasswordRequest;
import com.adkp.fuexchange.response.OrderDetailResponse;
import com.adkp.fuexchange.response.ResponseObject;

import java.util.List;

public interface RegisteredStudentService {

    RegisteredStudentDTO viewProfile(Integer registeredStudentId);

    ResponseObject<Object> updatePassword(UpdatePasswordRequest updatePasswordRequest);

    List<OrderDetailResponse> getOrdersDetailByRegisteredStudentId(Integer registeredStudentId);

    RegisteredStudentDTO updateDeliveryAddress(Integer registeredStudentId, String deliveryAddress);

    List<RegisteredStudentDTO> filterRegisteredStudent(
            String studentName
    );

    RegisteredStudentDTO updateStatusRegisteredStudent(
            Integer registeredStudentId,
            Integer isActive
    );
}
