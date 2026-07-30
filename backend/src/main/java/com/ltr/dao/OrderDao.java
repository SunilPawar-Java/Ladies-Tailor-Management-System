package com.ltr.dao;

import com.ltr.model.BodyMeasurement;
import com.ltr.product.ProductDao;
import com.ltr.user.UsersDao;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDao {

    private Long id;
    private Double totalAmount;
    private LocalDateTime placedDate;
    private String status;
    private BodyMeasurement bodyMeasurement;
    private DesignDao designDao;
    private ProductDao productDao;
    private UsersDao usersDao;
}
