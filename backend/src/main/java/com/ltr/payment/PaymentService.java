package com.ltr.payment;

import com.ltr.exception.OrderNotFoundException;
import com.ltr.model.Orders;
import com.ltr.model.PaymentStatus;
import com.ltr.repository.OrdersRepository;
import com.ltr.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    @Value("${razorpay.test-key}")
    private String keyId;
    @Value("${razorpay.secret-key}")
    private String keySecret;
    private final PaymentRepository paymentRepository;
    private final OrdersRepository ordersRepository;

    public PaymentService(PaymentRepository paymentRepository, OrdersRepository ordersRepository) {
        this.paymentRepository = paymentRepository;
        this.ordersRepository = ordersRepository;
    }

    @Transactional
    public String createPayment(Long orderId) {
        RazorpayClient client;
        JSONObject JObject;
        Order order = null;
        Orders orders = ordersRepository.findById(orderId).orElseThrow(() ->
                new OrderNotFoundException("CreatePayment--->Order By id Not found"));
        orders.setTotalAmount((orders.getProduct().getPrice() - ((orders.getProduct().getPrice() * orders.getProduct().getDiscountPercentage()) / 100)));
        try{
            client  = new RazorpayClient(keyId, keySecret);
            JObject = new JSONObject();
            JObject.put("amount", orders.getTotalAmount() * 100);
            JObject.put("currency", "INR");
            JObject.put("receipt", String.valueOf(orderId));
            order = client.orders.create(JObject);

        }catch (RazorpayException e){
            System.out.println("Razorpay ----> PaymentService  -----> createPayment()");
            e.printStackTrace();
        }
        Payment payment = new Payment();
        payment.setAmount(orders.getTotalAmount());
        payment.setStatus(PaymentStatus.PAYMENT_PENDING.toString());
        payment.setOrder(orders);
        System.out.println(order.get("id").toString());
        payment.setRazorpayOrderId(order.get("id"));
        payment = paymentRepository.save(payment);
        orders.setPayment(payment);
        ordersRepository.save(orders);
        assert order != null;
        return order.toString();
    }

    @Transactional
    public String verifyPayment(PaymentVerificationRequest paymentVerificationRequest) {
        String payload = paymentVerificationRequest.rozOrderId()+"|"+paymentVerificationRequest.rozPaymentId();
        boolean valid = false;
        try {
            valid = Utils.verifySignature(
                    payload,
                    paymentVerificationRequest.rozSignature(),
                    keySecret
            );
        }catch (Exception e){
            e.printStackTrace();
        }
        if (valid){
            Orders order = ordersRepository.findById(paymentVerificationRequest.orderId())
                    .orElseThrow(() -> new OrderNotFoundException("Not Found For ID: "+paymentVerificationRequest.orderId()));
            Payment payment = paymentRepository.findById(order.getPayment().getId())
                    .orElseThrow(()-> new RuntimeException("Payment not fount for ID: "+ order.getPayment().getId()));
            payment.setPaidAt(LocalDateTime.now());
            payment.setRazorpayOrderId(paymentVerificationRequest.rozOrderId());
            payment.setRazorpayPaymentId(paymentVerificationRequest.rozPaymentId());
            payment.setRazorpaySignature(paymentVerificationRequest.rozSignature());
            payment.setStatus(PaymentStatus.PAYMENT_COMPLETE.toString());
            order.setPayment(payment);
            order.setStatus(PaymentStatus.PAYMENT_COMPLETE.toString());
            ordersRepository.save(order);
        }

        return valid ? "Payment Successful" : "Payment Failed!";
    }


    @Transactional
    public String markFailed(PaymentFailureRequest paymentFailureRequest) {
        System.out.println(paymentFailureRequest.rozOrderId());
        Payment payment = paymentRepository.findByRazorpayOrderId(paymentFailureRequest.rozOrderId())
                .orElseThrow(()-> new RuntimeException("Payment Not Found (markFailed)"));
        payment.setStatus(PaymentStatus.PAYMENT_FAILED.toString());
        payment.setFailureCode(paymentFailureRequest.failureCode());
        payment.setFailureSource(paymentFailureRequest.failureSource());
        payment.setFailureReason(paymentFailureRequest.failureReason());
        payment.getOrder().setStatus(PaymentStatus.PAYMENT_FAILED.toString());
        paymentRepository.save(payment);
        return "Payment Failed!";
    }
}