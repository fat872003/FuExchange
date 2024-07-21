package com.adkp.fuexchange.controller.report;

import com.adkp.fuexchange.dto.ReportPostProductDTO;
import com.adkp.fuexchange.dto.ReportProductTypeDTO;
import com.adkp.fuexchange.request.SendReportPostRequest;
import com.adkp.fuexchange.response.ResponseObject;
import com.adkp.fuexchange.service.ReportService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report-post-product")
@Tag(name = "Report Post Product")
@Validated
public class ReportPostProductController {

    private final ReportService reportService;

    @Autowired
    public ReportPostProductController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/view-all")
    public ResponseObject<Object> getAllReportPostProduct() {

        List<ReportPostProductDTO> getAll = reportService.getAllReportPostProduct();

        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xem thông tin thành công!")
                .data(getAll)
                .build();
    }

    @GetMapping("/all-type")
    public ResponseObject<Object> allReportPostProductType() {

        List<ReportProductTypeDTO> getAllPostProductType = reportService.allReportPostProductType();

        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xem thông tin thành công!")
                .data(getAllPostProductType)
                .build();
    }

    @GetMapping("/filter")
    public ResponseObject<Object> filterReportPostProduct(
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "reportProductTypeId", required = false) Integer reportProductTypeId,
            @RequestParam(value = "reportStatusId", required = false) Integer reportStatusId
    ) {

        List<ReportPostProductDTO> filterReportPostProduct = reportService.filterReportPostProduct(
                productName,
                reportProductTypeId,
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
            @RequestBody @Valid SendReportPostRequest sendReportPostRequest
    ) {

        int status = HttpStatus.OK.value();
        String message = HttpStatus.OK.name();
        String content = "Gửi tố cáo thành công. Vui lòng chờ xác nhận!";

        ReportPostProductDTO sentReport = reportService.sendReportPostProduct(sendReportPostRequest);

        if (sentReport == null) {
            status = HttpStatus.BAD_REQUEST.value();
            message = HttpStatus.BAD_REQUEST.name();
            content = "Bạn đã tố cáo bài đăng này rồi!";
        }
        return ResponseObject.builder()
                .status(status)
                .message(message)
                .content(content)
                .data(sentReport)
                .build();
    }
}
