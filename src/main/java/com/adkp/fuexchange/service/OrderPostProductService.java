package com.adkp.fuexchange.service;

import com.adkp.fuexchange.response.DashBoardResponse;

import java.util.List;

public interface OrderPostProductService {
    List<DashBoardResponse> dashboardEachPost(Integer sellerId);
}
