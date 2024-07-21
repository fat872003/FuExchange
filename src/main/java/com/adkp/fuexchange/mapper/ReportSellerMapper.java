package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.ReportSellerDTO;
import com.adkp.fuexchange.pojo.ReportSeller;
import com.adkp.fuexchange.pojo.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ReportSellerTypeMapper.class, ReportStatusMapper.class})
public interface ReportSellerMapper {

    @Mapping(source = "reportSellerId", target = "reportSellerId")
    @Mapping(source = "buyerId.registeredStudentId", target = "buyerId")
    @Mapping(source = "buyerId.studentId", target = "buyerName", qualifiedByName = "studentToBuyerName")
    @Mapping(source = "sellerId.sellerId", target = "sellerId")
    @Mapping(source = "sellerId.registeredStudentId.studentId", target = "sellerName", qualifiedByName = "studentToBuyerName")
    @Mapping(source = "reportSellerTypeId", target = "reportSellerType")
    @Mapping(source = "reportStatusId", target = "reportStatus")
    @Mapping(source = "createTime", target = "createTime")
    @Mapping(source = "content", target = "content")
    ReportSellerDTO toReportSellerDTO(ReportSeller reportSeller);

    List<ReportSellerDTO> toReportSellerDTOList(List<ReportSeller> reportSellerList);

    @Named("studentToBuyerName")
    default String studentToBuyerName(Student student) {
        return student.getFirstName() + " " + student.getLastName();
    }
}
