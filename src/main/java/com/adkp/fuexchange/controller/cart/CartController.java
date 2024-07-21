package com.adkp.fuexchange.controller.cart;

import com.adkp.fuexchange.dto.CartPostDTO;
import com.adkp.fuexchange.request.CartRequest;
import com.adkp.fuexchange.request.UpdateCartRequest;
import com.adkp.fuexchange.response.ResponseObject;
import com.adkp.fuexchange.service.CartPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@Tag(name = "Shopping Cart")
@Validated
public class CartController {
    private final CartPostService cartPostService;

    @Autowired
    public CartController(CartPostService cartPostService) {
        this.cartPostService = cartPostService;
    }

    @GetMapping("/{registeredStudentId}")
    @Operation(summary = "Get cart by studentId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data sample: DE170001",
                    content = {@Content(mediaType = "application/json")}),
    })
    public ResponseObject<Object> viewCartPostByStudentId(@PathVariable("registeredStudentId") Integer registeredStudentId) {

        int status = HttpStatus.OK.value();
        String message = HttpStatus.OK.name();
        String content = "Xem thông tin thành công!";

        List<CartPostDTO> cartPostDTO =
                cartPostService.viewCartPostProductByStudentId(registeredStudentId);

        if (cartPostDTO == null) {
            status = HttpStatus.BAD_REQUEST.value();
            message = HttpStatus.BAD_REQUEST.name();
            content = "Xem thông tin thất bại!";
        }

        return ResponseObject.builder()
                .status(status)
                .message(message)
                .content(content)
                .data(cartPostDTO)
                .build();
    }

    @PostMapping("/add-to-cart")
    public ResponseObject<Object> addPostProductToCart(@Valid @RequestBody CartRequest cartRequest) {

        int status = HttpStatus.OK.value();
        String message = HttpStatus.OK.name();
        String content = "Thêm giỏ hàng thành công";

        List<CartPostDTO> cartPosts = cartPostService.addToCart(cartRequest);

        if (cartPosts == null) {
            status = HttpStatus.BAD_REQUEST.value();
            message = HttpStatus.BAD_REQUEST.name();
            content = "Thêm giỏ hàng thất bại!";
        }
        return ResponseObject.builder()
                .status(status)
                .message(message)
                .content(content)
                .data(cartPosts)
                .build();
    }

    @PutMapping("/update-cart")
    public ResponseObject<Object> updateCart(@Valid @RequestBody UpdateCartRequest updateCartRequest) {

        int status = HttpStatus.OK.value();
        String message = HttpStatus.OK.name();
        String content = "Cập nhât giỏ hàng thành công";

        List<CartPostDTO> cartPosts = cartPostService.updateCart(updateCartRequest);

        if (cartPosts == null) {
            status = HttpStatus.BAD_REQUEST.value();
            message = HttpStatus.BAD_REQUEST.name();
            content = "Cập nhât hàng thất bại!";
        }
        return ResponseObject.builder()
                .status(status)
                .message(message)
                .content(content)
                .data(cartPosts)
                .build();
    }

    @DeleteMapping("/cart-delete")
    public ResponseObject<Object> deletePostProduct(@Valid @RequestBody CartRequest cartRequest) {

        boolean removeResult = cartPostService.removeFromCart(cartRequest);

        int status = HttpStatus.OK.value();
        String message = HttpStatus.OK.name();
        String content = "Xóa sản phẩm ra khỏi giỏ hàng thành công!";

        if (!removeResult) {
            status = HttpStatus.BAD_REQUEST.value();
            message = HttpStatus.BAD_REQUEST.name();
            content = "Xóa sản phẩm ra khỏi giỏ hàng thất bại hoặc không có sản phẩm cần xóa!";
        }

        return ResponseObject.builder()
                .status(status)
                .message(message)
                .content(content)
                .build();
    }

}
