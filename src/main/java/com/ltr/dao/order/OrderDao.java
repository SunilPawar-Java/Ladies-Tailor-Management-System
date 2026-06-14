package com.ltr.dao.order;

import com.ltr.dao.UsersDao;
import com.ltr.entity.orders.BodyMeasurement;
import com.ltr.entity.products.Products;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDao {

    private Long id;
    private Double totalAmount;
    private LocalDateTime placedDate;
    private String status;
    private BodyMeasurement bodyMeasurement;
    private Products product;

    private UsersDao usersDao;
}
