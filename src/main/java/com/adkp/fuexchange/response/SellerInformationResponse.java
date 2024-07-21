package com.adkp.fuexchange.response;

import com.adkp.fuexchange.dto.SellerDTO;
import com.adkp.fuexchange.mapper.SellerMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellerInformationResponse {
    private SellerDTO sellerTO;
    private RegisteredStudentInformationResponse registeredStudentInformationResponse;
    private String deliveryAddress;

    private  int registeredStudentId;

}
