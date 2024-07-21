package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.ReportSellerTypeDTO;
import com.adkp.fuexchange.pojo.ReportSellerType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReportSellerTypeMapper {

    @Mapping(source = "reportSellerTypeId", target = "reportSellerTypeId")
    @Mapping(source = "reportTypeName", target = "reportTypeName")
    ReportSellerTypeDTO toReportSellerTypeDTO(ReportSellerType reportSellerType);

    List<ReportSellerTypeDTO> toReportSellerTypeDTOList(List<ReportSellerType> reviewList);
}
