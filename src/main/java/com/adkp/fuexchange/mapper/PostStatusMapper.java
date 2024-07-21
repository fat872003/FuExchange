package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.PostStatusDTO;
import com.adkp.fuexchange.pojo.PostStatus;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostStatusMapper {

    @Mapping(source = "postStatusId", target = "postStatusId")
    @Mapping(source = "postStatusName", target = "postStatusName")
    PostStatusDTO toPostStatusDTO(PostStatus postStatus);

    @InheritInverseConfiguration(name = "toPostStatusDTO")
    PostStatus toPostStatus(PostStatusDTO postStatusDTO);
}
