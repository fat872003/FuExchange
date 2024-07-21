package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.ProductImageDTO;
import com.adkp.fuexchange.pojo.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductImageMapper {

    @Mapping(source = "productImageId", target = "productImageId")
    @Mapping(source = "imageUrl", target = "imageUrl")
    ProductImageDTO toProductImageDTO(ProductImage productImage);

    List<ProductImageDTO> toProductImageDTOList(List<ProductImage> productImageList);

}
