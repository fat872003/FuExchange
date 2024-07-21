package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.ReportStatusDTO;
import com.adkp.fuexchange.pojo.ReportStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReportStatusMapper {

    @Mapping(source = "reportStatusId", target = "reportStatusId")
    @Mapping(source = "reportStatusName", target = "reportStatusName")
    ReportStatusDTO toReportStatusDTO(ReportStatus reportStatus);

    List<ReportStatusDTO> toReportStatusDTOList(List<ReportStatus> reviewList);
}
