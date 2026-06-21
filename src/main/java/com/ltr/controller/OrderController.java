package com.ltr.controller;
import com.ltr.dao.OrderDao;
import com.ltr.model.Orders;
import com.ltr.service.OrderService;
import com.ltr.service.security.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final AuthService authService;

    public OrderController(OrderService orderService, AuthService authService) {
        this.orderService = orderService;
        this.authService = authService;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody Orders order){
        return ResponseEntity.ok(Map.of("message" ,orderService.saveOrder(order)));
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/myorders")
    public ResponseEntity<?> myAllOrders(){
        Long userId = userId = authService.getUserId();
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/userid/{id}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(orderService.getOrdersByUserId(id));
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody OrderDao orderDao){
        return ResponseEntity.ok(orderService.updateOrderById(id, orderDao));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @PatchMapping("/update/status/{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable Long id, @RequestParam("status") String status){
        return ResponseEntity.ok(orderService.cancelOrder(id,status));
    }
}