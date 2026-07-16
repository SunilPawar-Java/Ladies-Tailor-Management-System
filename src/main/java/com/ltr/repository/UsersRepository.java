package com.ltr.repository;

import com.ltr.model.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    boolean existsByPhone(String phone);

    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Users u SET u.password = :password WHERE u.username = :username")
    void updatePassword(@Param("username") String username, @Param("password") String password);

    @Modifying
    @Transactional
    @Query("UPDATE Users u SET u.username = :newUsername WHERE u.username = :username")
    void updateUsername(@Param("username") String username, @Param("newUsername") String newUsername);

    Optional<Users> findByUsername(String username);

    boolean existsByUsername(String username);

    void deleteByUsername(String username);

    Optional<Users> findByUsernameOrEmailOrPhone(String username, String email, String phone);
}
