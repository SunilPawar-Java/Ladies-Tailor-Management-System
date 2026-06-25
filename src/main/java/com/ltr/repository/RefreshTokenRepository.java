package com.ltr.repository;

import com.ltr.model.security.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    RefreshToken findByUser_Id(Long userId);

    void deleteByRefreshToken(String refreshToken);
}
