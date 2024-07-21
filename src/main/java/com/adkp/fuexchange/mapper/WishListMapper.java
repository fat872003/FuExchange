package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.WishListDTO;
import com.adkp.fuexchange.pojo.WishList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {PostProductMapper.class, RegisteredStudentMapper.class}
)
public interface WishListMapper {

    @Mapping(source = "wishListId", target = "wishListId")
    @Mapping(source = "postProductId", target = "postProduct")
    @Mapping(source = "registeredStudentId", target = "registeredStudent")
    @Mapping(source = "createTime", target = "createTime")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "active", target = "active")
    WishListDTO toWishListDTO(WishList wishList);

    List<WishListDTO> toWishListDTOList(List<WishList> wishListList);
}
