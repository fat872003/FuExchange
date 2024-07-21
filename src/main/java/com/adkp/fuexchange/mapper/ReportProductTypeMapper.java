package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.ReportProductTypeDTO;
import com.adkp.fuexchange.pojo.ReportProductType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReportProductTypeMapper {

    @Mapping(source = "reportProductTypeId", target = "reportProductTypeId")
    @Mapping(source = "reportProductTypeName", target = "reportProductTypeName")
    @Mapping(source = "description", target = "description")
    ReportProductTypeDTO toReportProductTypeDTO(ReportProductType reportProductType);

    List<ReportProductTypeDTO> toReportProductTypeDTOList(List<ReportProductType> reportProductTypeList);
}
