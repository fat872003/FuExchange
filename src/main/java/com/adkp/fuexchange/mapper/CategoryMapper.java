package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.CategoryDTO;
import com.adkp.fuexchange.pojo.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "categoryName", target = "categoryName")
    @Mapping(source = "imageUrl", target = "imageUrl")
    CategoryDTO toCategoryDTO(Category category);

    List<CategoryDTO>  toCategoryDTOList(List<Category> categoryList);

}
