package com.ltr.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDao {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private LocalDateTime registrationDate;
}
