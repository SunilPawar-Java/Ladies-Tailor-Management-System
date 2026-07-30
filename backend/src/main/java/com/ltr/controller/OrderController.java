package com.ltr.controller;
import com.ltr.dao.OrderDao;
import com.ltr.model.OrderRequest;
import com.ltr.service.OrderService;
import com.ltr.security.AuthService;
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
    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest order){
        System.out.println("------>  "+ order.getMessage());
        return ResponseEntity.ok(Map.of(
                "orderId" ,orderService.saveOrder(order),
                "message", "Order Saved but PAYMENT_PENDING!"
        ));
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/myorders")
    public ResponseEntity<?> myAllOrders(){
        return ResponseEntity.ok(orderService.getOrdersByUserId(authService.getUserId()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders(){
        System.out.println("All Orders");
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