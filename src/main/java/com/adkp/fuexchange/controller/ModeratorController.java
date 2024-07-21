package com.adkp.fuexchange.controller;

import com.adkp.fuexchange.dto.*;
import com.adkp.fuexchange.request.UpdatePostStatus;
import com.adkp.fuexchange.request.UpdateReportPostRequest;
import com.adkp.fuexchange.request.UpdateReportSellerRequest;
import com.adkp.fuexchange.response.MetaResponse;
import com.adkp.fuexchange.response.ResponseObject;
import com.adkp.fuexchange.service.ModeratorService;
import com.adkp.fuexchange.service.PostProductService;
import com.adkp.fuexchange.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moderator")
@Tag(name = "Moderator")
@Validated
public class ModeratorController {

    private final ModeratorService moderatorService;

    private final PostProductService postProductService;

    private final ReportService reportService;

    @Autowired
    public ModeratorController(ModeratorService moderatorService, PostProductService postProductService, ReportService reportService) {
        this.moderatorService = moderatorService;
        this.postProductService = postProductService;
        this.reportService = reportService;
    }

    @GetMapping("/view-register-to-seller-request")
    public ResponseObject<Object> getRegisterToSellerRequest() {
        List<SellerDTO> sellerDTO = moderatorService.viewRegisterToSellerRequest();
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xem thông tin thành công!")
                .data(sellerDTO)
                .build();
    }

    @GetMapping("/view-create-order-request")
    public ResponseObject<Object> viewCreateOrderRequest() {

        List<OrdersDTO> ordersDTO = moderatorService.viewCreateOrderRequest();

        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xem thông tin thành công!")
                .data(ordersDTO)
                .build();
    }

    @GetMapping("/view-create-post-product")
    public ResponseObject<Object> viewCreatePostProduct() {

        List<PostProductDTO> postProductDTO = moderatorService.viewCreatePostProductRequest();

        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xem thông tin thành công!")
                .data(postProductDTO)
                .build();
    }

    @Operation(summary = "Update status of post product")
    @PutMapping("/update/status-post-product")
    public ResponseObject<Object> updateStatusPostProduct(@RequestBody @Valid UpdatePostStatus updatePostStatus) {

        PostProductDTO postProductDTO = postProductService.updateStatusPostProduct(updatePostStatus);

        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Cập nhật thành công")
                .data(postProductDTO)
                .build();
    }


    @GetMapping("/filter-post-product")
    public ResponseObject<Object> filterPostProductForStaff(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "sellerName", required = false) String sellerName,
            @RequestParam(value = "postTypeId", required = false) Integer postTypeId,
            @RequestParam(value = "campusId", required = false) Integer campusId,
            @RequestParam(value = "postStatus", required = false) Integer postStatus
    ) {

        List<PostProductDTO> postProductDTOs = postProductService.filterPostProductForStaff(
                page - 1,
                sellerName,
                postTypeId,
                campusId,
                postStatus
        );

        long totalAfterFilter = postProductService.totalAfterFilter(
                sellerName,
                postTypeId,
                campusId,
                postStatus
        );

        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xem thêm thành công!")
                .data(postProductDTOs)
                .meta(
                        MetaResponse.builder()
                                .total(totalAfterFilter)
                                .build()
                )
                .build();
    }


    @PutMapping("/update-status-report-post")
    public ResponseObject<Object> updateStatusReportPostProduct(
            @RequestBody @Valid UpdateReportPostRequest updatePostProductRequest
    ) {

        int status = HttpStatus.OK.value();
        String message = HttpStatus.OK.name();
        String content = "Cập nhật thành công!";

        List<ReportPostProductDTO> updatedReport = reportService.updateStatusReportPostProduct(updatePostProductRequest);

        if (updatedReport == null) {
            status = HttpStatus.BAD_REQUEST.value();
            message = HttpStatus.BAD_REQUEST.name();
            content = "Cập nhật thất bại!";
        }

        return ResponseObject.builder()
                .status(status)
                .message(message)
                .content(content)
                .data(updatedReport)
                .build();
    }

    @PutMapping("/update-status-report-seller")
    public ResponseObject<Object> updateStatusReportSeller(
            @RequestBody @Valid UpdateReportSellerRequest updateReportSellerRequest
    ) {

        int status = HttpStatus.OK.value();
        String message = HttpStatus.OK.name();
        String content = "Cập nhật thành công!";

        List<ReportSellerDTO> updatedReport = reportService.updateStatusReportSeller(updateReportSellerRequest);

        if (updatedReport == null) {
            status = HttpStatus.BAD_REQUEST.value();
            message = HttpStatus.BAD_REQUEST.name();
            content = "Cập nhật thất bại!";
        }

        return ResponseObject.builder()
                .status(status)
                .message(message)
                .content(content)
                .data(updatedReport)
                .build();
    }
}
