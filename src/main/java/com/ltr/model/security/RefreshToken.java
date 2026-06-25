package com.ltr.model.security;

import com.ltr.model.Users;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken {
    @Id
    private String refreshToken;
    private Instant expiration;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;
}