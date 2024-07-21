package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.CartPostDTO;
import com.adkp.fuexchange.pojo.CartPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {CartMapper.class, PostProductMapper.class, VariationDetailMapper.class}
)
public interface CartPostMapper {

    @Mapping(source = "sttPostInCart", target = "sttPostInCart")
    @Mapping(source = "cartId", target = "cart")
    @Mapping(source = "postProductId", target = "postProduct")
    @Mapping(source = "variationDetailId", target = "variationDetail")
    @Mapping(source = "quantity", target = "quantity")
    CartPostDTO toCartPostDTO(CartPost cartPost);

    List<CartPostDTO> toCartPostDTOList(List<CartPost> cartPost);

}
