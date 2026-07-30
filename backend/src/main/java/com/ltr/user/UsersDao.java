package com.ltr.user;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
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
