package com.adkp.fuexchange.controller.student;

import com.adkp.fuexchange.dto.PostProductDTO;
import com.adkp.fuexchange.request.CreatePostProductRequest;
import com.adkp.fuexchange.request.UpdatePostProductRequest;
import com.adkp.fuexchange.response.ResponseObject;
import com.adkp.fuexchange.service.PostProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post-product")
@Tag(name = "Post of Product")
@Validated
public class PostProductController {
    private final PostProductService postProductService;

    @Autowired
    public PostProductController(PostProductService postProductService) {
        this.postProductService = postProductService;
    }

    @Operation(summary = "Filter post product all case")
    @GetMapping("/{current}")
    public ResponseObject<Object> viewMorePostProduct(
            @PathVariable("current") int current,
            @RequestParam(value = "campusId", required = false) Integer campusId,
            @RequestParam(value = "postTypeId", required = false) Integer postTypeId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "categoryId", required = false) Integer categoryId
    ) {
        return postProductService.viewMorePostProduct(current, campusId, postTypeId, name, categoryId);
    }

    @GetMapping("/seller-post-product/{sellerId}")
    public ResponseObject<Object> getPostProductBySellerId(@PathVariable("sellerId") int sellerId) {
        return postProductService.getPostProductBySellerId(sellerId);
    }

    @GetMapping("/seller-post-product-by-regId/{registeredStudentId}")
    public ResponseObject<Object> getPostProductByRegisteredStudentId(@PathVariable("registeredStudentId") int registeredStudentId) {
        return postProductService.getPostProductByRegisteredStudentId(registeredStudentId);
    }

    @Operation(summary = "Get post product by postProductId")
    @GetMapping("/detail/{postProductId}")
    public ResponseObject<Object> getPostProductByPostProductId(@PathVariable("postProductId") int postProductId) {
        return postProductService.getPostProductById(postProductId);
    }

    @Operation(summary = "Update post product for all information")
    @PutMapping("/update")
    public ResponseObject<Object> updatePostProduct(
            @RequestBody @Valid UpdatePostProductRequest updatePostProductRequest
    ) {
        PostProductDTO postProduct = postProductService.updatePostProduct(updatePostProductRequest);

        int status = HttpStatus.OK.value();
        String message = HttpStatus.OK.name();
        String content = "Cập nhật thông tin thành công!";

        if (postProduct == null) {
            status = HttpStatus.BAD_REQUEST.value();
            message = HttpStatus.BAD_REQUEST.name();
            content = "Bài post đang có đơn hàng chưa được xử lý. Vui lòng xử lý đơn hàng trước khi xóa!";
        }

        return ResponseObject.builder()
                .status(status)
                .message(message)
                .content(content)
                .data(postProduct)
                .build();
    }

    @Operation(summary = "Create post product")
    @PostMapping("/create")
    public ResponseObject<Object> createPostProduct(
            @RequestBody @Valid CreatePostProductRequest createPostProductRequest
    ) {

        int status = HttpStatus.OK.value();
        String message = HttpStatus.OK.name();
        String content = "Tạo bài post thành công. Vui lòng chờ chúng tôi xét duyệt!";

        PostProductDTO postProductDTO =
                postProductService.createPostProduct(createPostProductRequest);

        if (postProductDTO == null) {
            status = HttpStatus.BAD_REQUEST.value();
            message = HttpStatus.BAD_REQUEST.name();
            content = "Tạo bài post thất bại. Vui lòng tạo lại!";
        }

        return ResponseObject.builder()
                .status(status)
                .message(message)
                .content(content)
                .data(postProductDTO)
                .build();
    }

}
