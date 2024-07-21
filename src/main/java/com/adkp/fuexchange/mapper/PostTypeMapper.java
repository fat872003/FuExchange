package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.PostTypeDTO;
import com.adkp.fuexchange.pojo.PostType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostTypeMapper {

    @Mapping(source = "postTypeId", target = "postTypeId")
    @Mapping(source = "postTypeName", target = "postTypeName")
    PostTypeDTO toPostTypeDTO(PostType postType);

    List<PostTypeDTO> toPostTypeDTOList(List<PostType> postTypeList);

}
