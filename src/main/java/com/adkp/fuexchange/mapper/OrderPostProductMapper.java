package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.OrderPostProductDTO;
import com.adkp.fuexchange.pojo.OrderPostProduct;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {OrdersMapper.class, VariationDetailMapper.class, PostProductMapper.class}
)
public interface OrderPostProductMapper {

    @Mapping(source = "sttOrder", target = "sttOrder")
    @Mapping(source = "orderId", target = "order")
    @Mapping(source = "postProductId", target = "postProduct")
    @Mapping(source = "variationDetailId", target = "variationDetail")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "priceBought", target = "priceBought")
    OrderPostProductDTO toOrderPostProductDTO(OrderPostProduct orderPostProduct);

    List<OrderPostProductDTO> toOrderPostProductDTOList(List<OrderPostProduct> orderPostProductList);

    @AfterMapping()
    default void afterMappingPostProduct(@MappingTarget OrderPostProductDTO orderPostProduct) {

        orderPostProduct.getPostProduct().getProduct().setSeller(null);
        orderPostProduct.getPostProduct().getProduct().setImage(null);
        orderPostProduct.getPostProduct().getProduct().setVariation(null);

    }
}
