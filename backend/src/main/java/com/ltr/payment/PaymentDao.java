package com.ltr.payment;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDao {

    private Long id;
    private String paymentStatus;
    private Double amount;
    private Double amountPaid;
}
