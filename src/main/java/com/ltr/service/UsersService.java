package com.ltr.service;
import com.ltr.dao.UsersDao;
import com.ltr.exception.UserAlreadyExistsException;
import com.ltr.model.Role;
import com.ltr.model.Users;
import com.ltr.exception.UserNotFoundException;
import com.ltr.mapper.Mapper;
import com.ltr.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users createUser(Users user){
        if (isExistsByPhone(user.getPhone())){
            throw new UserAlreadyExistsException(user.getPhone()+" Already Registered!");
        }
        if(isExistsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail()+" Already Registered!");
        }
        if (isExistByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException(user.getUsername()+" Already Registered!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_CUSTOMER);
        user.setRegistrationDate(LocalDateTime.now());
        return usersRepository.save(user);
    }

    public Users createAdmin(Users user){
        if (isExistsByPhone(user.getPhone())){
            throw new UserAlreadyExistsException(user.getPhone()+" Already Registered!");
        }
        if(isExistsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail()+" Already Registered!");
        }
        if (isExistByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException(user.getUsername()+" Already Registered!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_ADMIN);
        user.setRegistrationDate(LocalDateTime.now());
        return usersRepository.save(user);
    }

    public UsersDao getUser(String username){
        Users user = usersRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User Does Not Exist For Username = " + username));
        return Mapper.mapToUserDao(user);
    }

    public UsersDao getUser(Long id){
        Users user = usersRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User Does Not Exist For id = " + id));
        return Mapper.mapToUserDao(user);
    }

    public List<UsersDao> getAllUsers(){
        List<Users> allUsers = usersRepository.findAll();
        return Mapper.mapToUserDao(allUsers);
    }

    public Users getUserByUsername(String username){
        return usersRepository.findByUsername(username).orElseThrow(()->
                new UserNotFoundException("User Not Found For Username -> " + username));
    }

    public Users getUserByUsernameOrEmailOrPhone(String identity){
        return usersRepository.findByUsernameOrEmailOrPhone(identity, identity, identity).orElseThrow(()-> new UserNotFoundException("User Not Found For "+identity));
    }

    public boolean isExistsByUsername(String username){
        return usersRepository.existsByUsername(username);
    }

    public boolean isExistById(Long id){
        return usersRepository.existsById(id);
    }

    public boolean isExistByUsername(String username){
        return usersRepository.existsByUsername(username);
    }

    public boolean isExistsByPhone(String phone){
        return usersRepository.existsByPhone(phone);
    }

    public boolean isExistsByEmail(String email){
        return usersRepository.existsByEmail(email);
    }


    public String updateUserProfile(String username, UsersDao usersDao){
        Users user = usersRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException("User not found"));
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setFullName(usersDao.getFullName());
        user.setPhone(usersDao.getPhone());
        user.setEmail(usersDao.getEmail());
        user.setRegistrationDate(user.getRegistrationDate());
        usersRepository.save(user);
        return "Profile Updated Successfully...";
    }

    public String updatePassword(String username, String password){
        usersRepository.updatePassword(username, passwordEncoder.encode(password));
        return "Password Changed Successfully...";
    }

    public String changeUsername(String username, String newUsername) {
        usersRepository.updateUsername(username, newUsername);
        return "Username Changed Successfully...";
    }

    @Transactional
    public String deleteAccount(String username){
        if (!isExistByUsername(username))
            return "User Not Found!";
        usersRepository.deleteByUsername(username);
        return "Account Deleted SuccessFully...";
    }

    public String deleteAccount(Long id){
        usersRepository.deleteById(id);
        return "Account Deleted SuccessFully...";
    }

}
