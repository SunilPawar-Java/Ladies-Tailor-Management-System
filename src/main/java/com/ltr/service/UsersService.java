package com.ltr.service;

import com.ltr.dao.UsersDao;
import com.ltr.entity.users.Role;
import com.ltr.entity.users.Users;
import com.ltr.exception.classes.UserNotFoundException;
import com.ltr.repository.user.UsersRepository;
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
        UsersDao usersDetails = new UsersDao();
        usersDetails.setId(user.getId());
        usersDetails.setFullName(user.getFullName());
        usersDetails.setPhone(user.getPhone());
        usersDetails.setEmail(user.getEmail());
        usersDetails.setRegistrationDate(user.getRegistrationDate());
        return usersDetails;
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
