package com.ltr.dao;

import com.ltr.model.BodyMeasurement;
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
    private ProductDao productDao;
    private UsersDao usersDao;
}
