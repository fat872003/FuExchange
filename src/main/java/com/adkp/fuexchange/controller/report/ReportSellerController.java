package com.adkp.fuexchange.controller.report;

import com.adkp.fuexchange.dto.ReportPostProductDTO;
import com.adkp.fuexchange.dto.ReportSellerDTO;
import com.adkp.fuexchange.dto.ReportSellerTypeDTO;
import com.adkp.fuexchange.request.SendReportPostRequest;
import com.adkp.fuexchange.request.SendReportSellerRequest;
import com.adkp.fuexchange.response.ResponseObject;
import com.adkp.fuexchange.service.ReportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report-seller")
@Tag(name = "Report Seller")
@Validated
public class ReportSellerController {

    private final ReportService reportService;

    public ReportSellerController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/view-all")
    public ResponseObject<Object> getAllReportPostProduct() {

        List<ReportSellerDTO> getAll = reportService.getAllReportSeller();

        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xem thông tin thành công!")
                .data(getAll)
                .build();
    }

    @GetMapping("/all-type")
    public ResponseObject<Object> allReportPostProductType() {

        List<ReportSellerTypeDTO> getAllPostProductType = reportService.allReportSellerType();

        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xem thông tin thành công!")
                .data(getAllPostProductType)
                .build();
    }

    @GetMapping("/filter")
    public ResponseObject<Object> filterReportPostProduct(
            @RequestParam(value = "sellerName", required = false) String sellerName,
            @RequestParam(value = "reportProductTypeId", required = false) Integer reportSellerType,
            @RequestParam(value = "reportStatusId", required = false) Integer reportStatusId
    ) {

        List<ReportSellerDTO> filterReportPostProduct = reportService.filterReportSeller(
                sellerName,
                reportSellerType,
                reportStatusId
        );

        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xem thông tin thành công!")
                .data(filterReportPostProduct)
                .build();
    }

    @PostMapping("/send-report")
    public ResponseObject<Object> sendReportPostProduct(
            @RequestBody @Valid SendReportSellerRequest sendReportSellerRequest
    ) {

        int status = HttpStatus.OK.value();
        String message = HttpStatus.OK.name();
        String content = "Gửi tố cáo thành công. Vui lòng chờ xác nhận!";

        ReportSellerDTO sentReport = reportService.sendReportSeller(sendReportSellerRequest);

        if (sentReport == null) {
            status = HttpStatus.BAD_REQUEST.value();
            message = HttpStatus.BAD_REQUEST.name();
            content = "Bạn đã tố cáo người bán này rồi!";
        }

        return ResponseObject.builder()
                .status(status)
                .message(message)
                .content(content)
                .data(sentReport)
                .build();
    }
}
