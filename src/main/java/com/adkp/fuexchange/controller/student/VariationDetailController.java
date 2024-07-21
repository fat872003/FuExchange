package com.adkp.fuexchange.controller.student;


import com.adkp.fuexchange.request.VariationDetailRequest;
import com.adkp.fuexchange.response.ResponseObject;
import com.adkp.fuexchange.service.VariationDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/variation-detail")
@Tag(name = "variation Detail")
@Validated
public class VariationDetailController {
    private  final VariationDetailService variationDetailService;

    public VariationDetailController(VariationDetailService variationDetailService) {
        this.variationDetailService = variationDetailService;
    }


    @DeleteMapping("/{variationDetailId}")
    public ResponseObject<Object> deleteVariationDetailByID(
            @PathVariable("variationDetailId") int variationDetailId

    ){
        variationDetailService.deleteVariationDetailByID(variationDetailId);
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xóa thành công")
                .build();
    }
}
