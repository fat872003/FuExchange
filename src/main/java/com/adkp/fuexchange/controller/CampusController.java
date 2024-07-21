package com.adkp.fuexchange.controller;

import com.adkp.fuexchange.pojo.Campus;
import com.adkp.fuexchange.request.CampusRequest;
import com.adkp.fuexchange.response.ResponseObject;
import com.adkp.fuexchange.service.CampusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/campus")
@Tag(name = "Campus")
@Validated
public class CampusController {

    private final CampusService campusService;

    @Autowired
    public CampusController(CampusService campusService) {
        this.campusService = campusService;
    }

    @Operation(summary = "Add campus")
    @PostMapping(value = "/add-campus", consumes = "application/json")
    public ResponseObject<Object> payOrders(@Valid @RequestBody CampusRequest campusRequest) {
        Campus campus = campusService.addCampus(campusRequest);
        if (campus == null) {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.value())
                    .message(HttpStatus.OK.name())
                    .content("Thêm campus thất bại!")
                    .build();
        }
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Thêm campus thành công!")
                .build();
    }
}
