package com.ltr.controller;

import com.ltr.module.Users;
import com.ltr.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthController {

    private final UsersService usersService;

    public AuthController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.createUser(user));
    }

    @PostMapping("/registeradmin")
    public Users registerAdmin(@RequestBody Users user) {
        return usersService.createAdmin(user);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(usersService.getUser(id));
    }

}
