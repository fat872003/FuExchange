package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.ProductDetailDTO;
import com.adkp.fuexchange.pojo.ProductDetail;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses ={ProductImageMapper.class}
)
public interface ProductDetailMapper {

    @Mapping(source = "productDetailId", target = "productDetailId")
    @Mapping(source = "productName", target = "productName")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "productImageId", target = "productImage")
    ProductDetailDTO toProductDetailDTO(ProductDetail productDetail);

    @InheritInverseConfiguration(name = "toProductDetailDTO")
    ProductDetail toProductDetail(ProductDetailDTO productDetailDTO);
}
