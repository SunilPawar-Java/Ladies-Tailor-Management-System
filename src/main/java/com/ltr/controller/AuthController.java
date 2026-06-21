package com.ltr.controller;

import com.ltr.model.security.LoginRequest;
import com.ltr.service.security.UserSecurityDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserSecurityDetailsService userSecurityDetailsService;

    public AuthController(AuthenticationManager authManager, UserSecurityDetailsService userSecurityDetailsService) {
        this.authManager = authManager;
        this.userSecurityDetailsService = userSecurityDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest httpRequest){
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(auth);
            SecurityContextHolder.setContext(securityContext);

            HttpSession httpSession = httpRequest.getSession(false);
            if (httpSession != null)
                httpSession.invalidate();

            httpSession = httpRequest.getSession(true);
            httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "message", "Login Successful..."
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getStackTrace());
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        SecurityContextHolder.clearContext();
        request.getSession(false).invalidate();
        return ResponseEntity.ok(Map.of("message", "Log Out successfully"));
    }

}
