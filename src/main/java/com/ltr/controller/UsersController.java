package com.ltr.controller;

import com.ltr.dao.UsersDao;
import com.ltr.model.Users;
import com.ltr.service.UsersService;
import com.ltr.service.security.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsersController {

    private final UsersService usersService;
    private final AuthService authService;

    public UsersController(UsersService usersService, AuthService authService) {
        this.usersService = usersService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.createUser(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registeradmin")
    public Users registerAdmin(@RequestBody Users user) {
        return usersService.createAdmin(user);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(){
       String username = authService.getUsername();
        System.out.println(username);
        return ResponseEntity.ok(usersService.getUser(username));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(usersService.getUser(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @PutMapping("/updateprofile")
    public ResponseEntity<?> updateUser(@RequestBody UsersDao usersDao){
        String username = authService.getUsername();
        return ResponseEntity.ok(usersService.updateUserProfile(username, usersDao));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @PatchMapping("/password")
    public ResponseEntity<?> changePassword(@RequestBody String password){
        String username = authService.getUsername();
        return ResponseEntity.ok(usersService.updatePassword(username, password));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @PatchMapping("/username")
    public ResponseEntity<?> changeUsername(@RequestBody String newUsername){
        String username = authService.getUsername();
        return ResponseEntity.ok(usersService.changeUsername(username, newUsername));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @DeleteMapping("/account")
    public ResponseEntity<?> deleteMyAccount(){
        String username = authService.getUsername();
        return ResponseEntity.ok(usersService.deleteAccount(username));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id){
        return ResponseEntity.ok(usersService.deleteAccount(id));
    }
}