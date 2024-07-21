package com.adkp.fuexchange.controller;

import com.adkp.fuexchange.dto.OrdersDTO;
import com.adkp.fuexchange.request.OrderUpdateRequest;
import com.adkp.fuexchange.response.ResponseObject;
import com.adkp.fuexchange.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Tag(name = "Order")
@Validated
public class OrdersController {

    private final OrderService orderService;


    @Autowired
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Update order")
    @PutMapping("/update")
    public ResponseObject<Object> updateOrder(@Valid @RequestBody OrderUpdateRequest orderUpdateRequest) {
        if (orderService.updateOrder(orderUpdateRequest) != null) {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.value())
                    .message(HttpStatus.OK.name())
                    .content("Cập nhật thành công!")
                    .build();
        }
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Cập nhật thất bại!")
                .data(orderService.updateOrder(orderUpdateRequest))
                .build();
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseObject<Object> deleteOrder(@PathVariable("orderId") Integer orderId) {
        OrdersDTO orderDeleted = orderService.deleteOrder(orderId);
        if (orderDeleted == null) {
            return ResponseObject.builder()
                    .status(HttpStatus.OK.value())
                    .message(HttpStatus.OK.name())
                    .content("Order không tồn tại!")
                    .build();
        }
        return ResponseObject.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .content("Xóa order thành công!")
                .data(orderDeleted)
                .build();
    }

}
