package com.ltr.payment;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/create-order/{orderId}")
    public String savePayment(@PathVariable("orderId") Long orderId){
        return paymentService.createPayment(orderId);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/verify")
    public String verifyPayment(@RequestBody PaymentVerificationRequest paymentVerificationRequest){
        return paymentService.verifyPayment(paymentVerificationRequest);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/failure")
    public String paymentFailure(@RequestBody PaymentFailureRequest paymentFailureRequest){
        System.out.println("paymentFailure");
        return paymentService.markFailed(paymentFailureRequest);
    }
}
