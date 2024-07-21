package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.OrderStatusDTO;
import com.adkp.fuexchange.pojo.OrderStatus;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderStatusMapper {
    @Mapping(source = "orderStatusId", target = "orderStatusId")
    @Mapping(source = "orderStatusName", target = "orderStatusName")
    OrderStatusDTO toOrderStatusDTO(OrderStatus orderStatus);

    @InheritInverseConfiguration(name = "toOrderStatusDTO")
    OrderStatus toOrderStatus(OrderStatusDTO orderStatusDTO);
}
