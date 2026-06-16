package com.ltr.service;

import com.ltr.dao.UsersDao;
import com.ltr.module.Role;
import com.ltr.module.Users;
import com.ltr.exception.UserNotFoundException;
import com.ltr.mapper.Mapper;
import com.ltr.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users createUser(Users user){
        if (isExistsByPhone(user.getPhone())){
            throw new UserNotFoundException("User Already Exists By Phone Number!");
        } else if(isExistsByEmail(user.getEmail())) {
            throw new UserNotFoundException("User Already Exists By Email ID!");
        }else {
            user.setRole(Role.ROLE_CUSTOMER);
            user.setRegistrationDate(LocalDateTime.now());
            return usersRepository.save(user);
        }
    }

    public UsersDao getUser(Long id){
        Users user = usersRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User Not Exists For User ID = " + id));
        return Mapper.mapToUserDao(user);
    }

    public boolean isExistsByPhone(String phone){
        return usersRepository.existsByPhone(phone);
    }

    public boolean isExistsByEmail(String email){
        return usersRepository.existsByEmail(email);
    }

    public Users createAdmin(Users user){
        user.setRole(Role.ROLE_ADMIN);
        user.setRegistrationDate(LocalDateTime.now());
        return usersRepository.save(user);
    }

 }
