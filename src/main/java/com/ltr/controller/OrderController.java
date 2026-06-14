package com.ltr.controller;
import com.ltr.entity.orders.Orders;
import com.ltr.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody Orders order){
        return ResponseEntity.ok(Map.of("message" ,orderService.saveOrder(order)));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/get/userid/{id}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(orderService.getOrdersByUserId(id));
    }
}