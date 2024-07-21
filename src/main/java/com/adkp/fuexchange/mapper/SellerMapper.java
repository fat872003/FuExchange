package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.SellerDTO;
import com.adkp.fuexchange.pojo.Seller;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {StudentMapper.class}
)
public interface SellerMapper {

    @Mapping(source = "sellerId", target = "sellerId")
    @Mapping(source = "registeredStudentId.studentId", target = "student")
    @Mapping(source = "bankingName", target = "bankingName")
    @Mapping(source = "bankingNumber", target = "bankingNumber")
    @Mapping(source = "isActive", target = "active")
    SellerDTO toSellerDTO(Seller seller);

    List<SellerDTO> toSellerDTOList(List<Seller> sellerList);

}
