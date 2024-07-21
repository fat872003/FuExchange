package com.adkp.fuexchange.controller.student;

import com.adkp.fuexchange.dto.ProductDTO;
import com.adkp.fuexchange.request.RegisterProductRequest;
import com.adkp.fuexchange.request.UpdateInformationProductRequest;
import com.adkp.fuexchange.response.ProductResponse;
import com.adkp.fuexchange.response.ResponseObject;
import com.adkp.fuexchange.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Tag(name = "Product")
@Validated
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Filter product")
    @GetMapping("/{current}")
    public ResponseObject<Object> viewMoreProduct(
            @PathVariable("current") int current,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "studentId", required = true) String studentId
    ) {
        return productService.topProductByUserIdAndName(studentId, name, current);

    }

    @Operation(summary = "Get detail product by productId")
    @GetMapping("detail/{productId}")
    public ResponseObject<Object> viewDetailProductByProductId(
            @PathVariable("productId") int productID
    ) {
        return productService.getProductByProductID(productID);
    }

    @Operation(summary = "Create a product")
    @PostMapping("/create-product")
    public ResponseObject<Object> createAProduct(
            @Valid @RequestBody RegisterProductRequest registerProductRequest
    ) {
        return productService.createProduct(registerProductRequest);
    }

    @PutMapping("/update-information")
    public ResponseObject<Object> updateInformation(
            @RequestBody @Valid UpdateInformationProductRequest updateInformationProductRequest
    ) {

        ProductDTO product = productService.updateProductInformation(updateInformationProductRequest);

        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Cập nhật thông tin sản phẩm thành công!")
                .data(product)
                .build();
    }

    @PostMapping("/get-by-variation")
    @Operation(summary = "Get product by variation detail")
    public ResponseObject<Object> getProductVariationId(@RequestBody List<Integer> variationDetailId) {

        ProductResponse productResponse = productService.getProductByVariationDetailId(variationDetailId);

        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .data(productResponse)
                .content("Lấy sản phẩm thành công")
                .build();
    }

    @PutMapping("/delete-product")
    @Operation(summary = "Delete product")
    public ResponseObject<Object> deleteProduct(
            @RequestBody Integer productId
    ) {

        int status = HttpStatus.OK.value();
        String message = HttpStatus.OK.name();
        String content = "Xóa sản phẩm thành công!";

        ProductDTO productDeleted = productService.deleteProduct(productId);

        if (productDeleted == null) {
            status = HttpStatus.BAD_REQUEST.value();
            message = HttpStatus.BAD_REQUEST.name();
            content = "Sản phẩm đang được bán hoặc đang chờ xác nhận được bán!";
        }
        return ResponseObject.builder()
                .status(status)
                .message(message)
                .content(content)
                .data(productDeleted)
                .build();
    }
}
