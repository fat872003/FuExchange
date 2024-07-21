package com.adkp.fuexchange.controller;

import com.adkp.fuexchange.response.ResponseObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dash-board")
@Tag(name = "Dashboard")
public class DashboardController {

    @GetMapping("/each-post")
    public ResponseObject<Object> dashboardPostBySellerId(@RequestParam int sellerId) {
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xem thông tin thành công!")
                .build();
    }

    @GetMapping("/all-post")
    public ResponseObject<Object> totalDashboardBySellerId(@RequestParam int sellerId) {

        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xem thông tin thành công!")
                .build();
    }
}
