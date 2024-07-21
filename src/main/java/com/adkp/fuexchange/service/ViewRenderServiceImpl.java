package com.adkp.fuexchange.service;

import com.adkp.fuexchange.mapper.CampusMapper;
import com.adkp.fuexchange.mapper.CategoryMapper;
import com.adkp.fuexchange.mapper.PostTypeMapper;
import com.adkp.fuexchange.repository.CampusRepository;
import com.adkp.fuexchange.repository.CategoryRepository;
import com.adkp.fuexchange.repository.PostStatusRepository;
import com.adkp.fuexchange.repository.PostTypeRepository;
import com.adkp.fuexchange.response.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ViewRenderServiceImpl implements ViewRenderService {
    private final CampusRepository campusRepository;
    private final PostTypeRepository postTypeRepository;
    private final CategoryRepository categoryTypeRepository;
    private final CampusMapper campusMapper;
    private final PostTypeMapper postTypeMapper;
    private final CategoryMapper categoryMapper;
    private final PostStatusRepository postStatusRepository;

    public ViewRenderServiceImpl(CampusRepository campusRepository, PostTypeRepository postTypeRepository, CategoryRepository categoryTypeRepository, CampusMapper campusMapper, PostTypeMapper postTypeMapper, CategoryMapper categoryMapper, PostStatusRepository postStatusRepository) {
        this.campusRepository = campusRepository;
        this.postTypeRepository = postTypeRepository;
        this.categoryTypeRepository = categoryTypeRepository;
        this.campusMapper = campusMapper;
        this.postTypeMapper = postTypeMapper;
        this.categoryMapper = categoryMapper;
        this.postStatusRepository = postStatusRepository;
    }

    @Override
    public ResponseObject<Object> viewAllCampus() {
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xem thông tin thành công!")
                .data(
                        campusMapper.toCampusDTOList(campusRepository.findAllCampus())
                )
                .build();
    }

    @Override
    public ResponseObject<Object> viewAllPostType() {
//        return postTypeMapper;
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xem thông tin thành công!")
                .data(
                        postTypeMapper.toPostTypeDTOList(postTypeRepository.findAllPostType())
                )
                .build();
    }

    @Override
    public ResponseObject<Object> viewAllCategoryType() {
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xem thông tin thành công!")
                .data(
                        categoryMapper.toCategoryDTOList(categoryTypeRepository.findAllCategoryProductType())
                )
                .build();
    }

    @Override
    public ResponseObject<Object> viewAllPostStatus() {
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xem thông tin thành công!")
                .data(
                        postStatusRepository.findAll()
                )
                .build();
    }
}
