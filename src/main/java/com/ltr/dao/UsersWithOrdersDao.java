package com.ltr.dao;

import com.ltr.entity.orders.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersWithOrdersDao {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private LocalDateTime registrationDate;

    private List<Orders> orders;
}
