package com.adkp.fuexchange.controller.student;

import com.adkp.fuexchange.dto.PostProductDTO;
import com.adkp.fuexchange.dto.SellerDTO;
import com.adkp.fuexchange.request.RegisterToSellerRequest;
import com.adkp.fuexchange.request.UpdateInformationSellerRequest;
import com.adkp.fuexchange.request.UpdatePostStatus;
import com.adkp.fuexchange.request.UpdateStatusRequest;
import com.adkp.fuexchange.response.ResponseObject;
import com.adkp.fuexchange.service.PostProductService;
import com.adkp.fuexchange.service.SellerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
@Tag(name = "Seller")
@Validated
public class SellerController {

    private final SellerService sellerService;

    private final PostProductService postProductService;

    @Autowired
    public SellerController(SellerService sellerService, PostProductService postProductService) {
        this.sellerService = sellerService;
        this.postProductService = postProductService;
    }

    @Operation(summary = "view order for seller by seller id")
    @GetMapping("/order/{sellerId}")
    public ResponseObject<Object> getOrderBySellerId(@PathVariable("sellerId") Integer sellerId) {
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .data(sellerService.getOrderBySellerId(sellerId))
                .content("Xem thành công!")
                .build();
    }

    @Operation(summary = "view all by seller")
    @GetMapping("/get-all")
    public ResponseObject<Object> getAllSeller() {

        List<SellerDTO> sellerDTOS = sellerService.getAllSeller();

        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .data(sellerDTOS)
                .content("Xem thành công!")
                .build();
    }

    @Operation(summary = "view order detail for seller by seller id")
    @GetMapping("/order-detail/{sellerId}/{orderId}")
    public ResponseObject<Object> getOrderDetailBySellerIdAndOrderId(
            @PathVariable("sellerId") Integer sellerId,
            @PathVariable("orderId") Integer orderId
    ) {
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .data(sellerService.getOrderDetailBySellerIdAndOrderId(sellerId, orderId))
                .content("Xem thành công!")
                .build();
    }

    @Operation(summary = "View profile of seller by sellerId")
    @GetMapping("/{sellerId}")
    public ResponseObject<Object> viewInformationSellerById(
            @PathVariable int sellerId
    ) {
        return sellerService.viewInformationSellerById(sellerId);
    }

    @Operation(summary = "View profile of seller by studentId")
    @GetMapping("/information/{studentId}")
    public ResponseObject<Object> viewInformationSellerByStudentId(
            @PathVariable("studentId") String studentId
    ) {
        if (sellerService.checkSellerByStudentID(studentId) == null) {
            return ResponseObject.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(HttpStatus.BAD_REQUEST.name())
                    .content("Sinh viên chưa đăng ký trở thành người bán!")
                    .build();
        }
        return sellerService.getInformationSellerByStudentId(studentId);
    }

    @Operation(summary = "Register to seller")
    @PostMapping("/register-to-seller")
    public ResponseObject<Object> registerToSeller(
            @Valid @RequestBody RegisterToSellerRequest registerToSellerRequest
    ) {
        return sellerService.registerToSeller(registerToSellerRequest);
    }

    @Operation(summary = "Update information of seller")
    @PutMapping("/update-information")
    public ResponseObject<Object> updateInformationSeller(@RequestBody UpdateInformationSellerRequest updateInformationSellerRequest) {

        if (
                updateInformationSellerRequest.getBankingName() != null
                        && updateInformationSellerRequest.getBankingNumber() != null
                        && updateInformationSellerRequest.getSellerId() != null
        ) {
            return sellerService.updateInformationSeller(updateInformationSellerRequest);
        }
        return ResponseObject.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.name())
                .content("Vui lòng nhập đầy đủ thông tin")
                .build();
    }

    @Operation(summary = "Update status of seller")
    @PutMapping("/update-status")
    public ResponseObject<Object> updateStatusSeller(@RequestBody @Valid UpdateStatusRequest updateStatusRequest) {

            return sellerService.updateStatusSeller(updateStatusRequest);
    }

    @Operation(summary = "delete of seller")
    @DeleteMapping("/{sellerId}")
    public ResponseObject<Object>deleteSellerByID(
            @PathVariable("sellerId") int sellerId
    ){
        sellerService.deleteSellerByID(sellerId);
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xóa thành công")
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
}
