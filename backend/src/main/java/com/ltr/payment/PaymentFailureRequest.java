package com.ltr.payment;

public record PaymentFailureRequest(
        String rozOrderId,
        String rozPaymentId,
        String failureCode,
        String failureSource,
        String failureReason
) {
}
