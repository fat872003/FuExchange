package com.adkp.fuexchange.service;

import com.adkp.fuexchange.response.ResponseObject;

public interface ViewRenderService {
    ResponseObject<Object> viewAllCampus();

    ResponseObject<Object> viewAllPostType();

    ResponseObject<Object> viewAllCategoryType();

    ResponseObject<Object> viewAllPostStatus();

}
