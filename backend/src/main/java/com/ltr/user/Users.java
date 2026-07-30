package com.ltr.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ltr.model.Orders;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("users-orders")
    private List<Orders> orders;
}