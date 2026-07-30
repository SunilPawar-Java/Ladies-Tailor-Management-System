package com.ltr.payment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ltr.model.Orders;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;
    private LocalDateTime paidAt;
    private String status;
    private Double amount;
    private String failureCode;
    private String failureReason;
    private String failureSource;

    @OneToOne(mappedBy = "payment")
    @JsonBackReference("orders-payment")
    private Orders order;
}
