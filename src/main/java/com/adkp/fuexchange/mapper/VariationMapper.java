package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.VariationDTO;
import com.adkp.fuexchange.pojo.Variation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VariationMapper {

    @Mapping(source = "variationId", target = "variationId")
    @Mapping(source = "variationName", target = "variationName")
    @Mapping(source = "variationDetailId", target = "variationDetail")
    VariationDTO toVariationDTO(Variation variation);

    List<VariationDTO> toVariationDTOList(List<Variation> variationList);

}
