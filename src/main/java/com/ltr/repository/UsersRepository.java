package com.ltr.repository;

import com.ltr.module.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    boolean existsByPhone(String phone);

    boolean existsByEmail(String email);
}
