package com.ltr.payment;

public record PaymentVerificationRequest(
        String rozOrderId,
        String rozPaymentId,
        String rozSignature,
        Long orderId
) {
}
