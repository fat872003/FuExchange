package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.PaymentMethodDTO;
import com.adkp.fuexchange.pojo.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMethodMapper {
    @Mapping(source = "paymentMethodId", target = "paymentMethodId")
    @Mapping(source = "paymentMethodName", target = "paymentMethodName")
    PaymentMethodDTO toPaymentMethodDTO(PaymentMethod paymentMethod);

    List<PaymentMethodDTO> toPaymentMethodDTOList(List<PaymentMethod> paymentMethodList);

}
