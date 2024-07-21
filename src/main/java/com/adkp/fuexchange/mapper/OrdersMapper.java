package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.OrdersDTO;
import com.adkp.fuexchange.pojo.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses ={RegisteredStudentMapper.class, OrderStatusMapper.class, PaymentMapper.class}
)
public interface OrdersMapper {
    @Mapping(source = "orderId", target = "orderId")
    @Mapping(source = "registeredStudentId.registeredStudentId", target = "registeredStudent")
    @Mapping(source = "orderStatusId", target = "orderStatus")
    @Mapping(source = "paymentId.paymentId", target = "paymentId")
    @Mapping(source = "createDate", target = "createDate")
    @Mapping(source = "completeDate", target = "completeDate")
    @Mapping(source = "description", target = "description")
    OrdersDTO toOrdersDTO(Orders orders);

    List<OrdersDTO> toOrdersDTOList(List<Orders> orders);

}
