package com.elitetech.springsecurity.controller;

import com.elitetech.springsecurity.dto.OrderDTO;
import com.elitetech.springsecurity.service.interfaces.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{userId}/create")
    public ResponseEntity<OrderDTO> createOrder(@PathVariable @Min(1) long userId, @Valid @RequestBody OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.createOrder(userId, orderDTO);
        return createdOrder != null ? ResponseEntity.ok(createdOrder) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/{orderId}/add-product/{productId}")
    public ResponseEntity<OrderDTO> addProductToOrder(@PathVariable @Min(1) long orderId, @PathVariable @Min(1) long productId) {
        OrderDTO updatedOrder = orderService.addProductToOrder(orderId, productId);
        return updatedOrder != null ? ResponseEntity.ok(updatedOrder) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable @Min(1) Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable @Min(1) Long id) {
        OrderDTO order = orderService.getOrderById(id);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
