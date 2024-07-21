package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.CampusDTO;
import com.adkp.fuexchange.pojo.Campus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CampusMapper {

    @Mapping(source = "campusId", target = "campusId")
    @Mapping(source = "campusName", target = "campusName")
    @Mapping(source = "imageUrl", target = "imageUrl")
    CampusDTO toCampusDTO(Campus campus);

    List<CampusDTO> toCampusDTOList(List<Campus> campusList);

}
