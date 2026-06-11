package com.ltr.repository.user;

import com.ltr.entity.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    boolean existsByPhone(String phone);

    boolean existsByEmail(String email);
}
