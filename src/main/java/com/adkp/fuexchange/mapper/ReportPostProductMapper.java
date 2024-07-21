package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.ReportPostProductDTO;
import com.adkp.fuexchange.pojo.ReportPostProduct;
import com.adkp.fuexchange.pojo.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ReportProductTypeMapper.class, ReportStatusMapper.class})
public interface ReportPostProductMapper {

    @Mapping(source = "reportPostProductId", target = "reportPostProductId")
    @Mapping(source = "buyerId.registeredStudentId", target = "buyerId")
    @Mapping(source = "buyerId.studentId", target = "buyerName", qualifiedByName = "studentToBuyerName")
    @Mapping(source = "postProductId.postProductId", target = "postProductId")
    @Mapping(source = "postProductId.productId.productDetailId.productName", target = "postProductName")
    @Mapping(source = "reportProductTypeId", target = "reportProductType")
    @Mapping(source = "reportStatusId", target = "reportStatus")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "createTime", target = "createTime")
    ReportPostProductDTO toReportPostProductDTO(ReportPostProduct reportPostProduct);

    List<ReportPostProductDTO> toReportPostProductDTOList(List<ReportPostProduct> reviewList);

    @Named("studentToBuyerName")
    default String studentToBuyerName(Student student) {
        return student.getFirstName() + " " + student.getLastName();
    }
}
