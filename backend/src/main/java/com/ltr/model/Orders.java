package com.ltr.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ltr.payment.Payment;
import com.ltr.product.Products;
import com.ltr.user.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalAmount;
    @Column(nullable = false)
    private LocalDateTime placedDate;
    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("users-orders")
    private Users user;

    @OneToOne
    @JoinColumn(name = "payment_id")
    @JsonManagedReference("orders-payment")
    private Payment payment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @JsonBackReference("products-orders")
    private Products product;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "design_id")
    @JsonManagedReference("orders-design")
    private Design design;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "body_measurement_id")
    @JsonManagedReference("orders-body_measurement")
    private BodyMeasurement bodyMeasurement;
}
