package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.ProductDTO;
import com.adkp.fuexchange.pojo.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.text.DecimalFormat;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ProductDetailMapper.class, ProductImageMapper.class, VariationMapper.class, SellerMapper.class})
public interface ProductMapper {

    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "productDetailId", target = "detail")
    @Mapping(source = "productDetailId.productImageId", target = "image")
    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "sellerId", target = "seller")
    @Mapping(source = "price", target = "price", qualifiedByName = "formatPrice")
    @Mapping(source = "variationId", target = "variation")
    ProductDTO toProductDTO(Product product);

    List<ProductDTO> toProductDTOList(List<Product> productList);

    @Named("formatPrice")
    default String formatPrice(double price) {
        DecimalFormat df = new DecimalFormat("#.000");
        return df.format(price);
    }

}
