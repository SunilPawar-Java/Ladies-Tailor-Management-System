package com.ltr.controller;

import com.ltr.model.security.LoginRequest;
import com.ltr.model.security.RefreshToken;
import com.ltr.model.security.UserSecurityDetails;
import com.ltr.service.security.JwtService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.http.HttpStatus;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
//import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwt;

    public AuthController(AuthenticationManager authManager, JwtService jwt) {
        this.authManager = authManager;
        this.jwt = jwt;
    }

    // JWT Authentication

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        UserSecurityDetails userSecurityDetails = (UserSecurityDetails) Objects.requireNonNull(auth.getPrincipal());
        String jwtToken = jwt.generateToken(userSecurityDetails);
        String refreshToken = jwt.generateRefreshToken(userSecurityDetails.getUsername());
        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(false)
                .sameSite("None")
                .path("/auth/refreshtoken")
                .maxAge(Duration.ofDays(7))
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
        return ResponseEntity.ok(Map.of(
                "message", "Login Successful",
                "JToken", jwtToken
        ));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@CookieValue("refreshToken") String refreshToken, HttpServletResponse response) {
        System.out.println(refreshToken);
        RefreshToken refreshToken1 = jwt.verifyRefreshToken(refreshToken);
        String jwtToken = jwt.generateToken(new UserSecurityDetails(refreshToken1.getUser()));
        refreshToken = jwt.generateRefreshToken(refreshToken1.getUser().getUsername());
        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(false)
                .sameSite("None")
                .path("/auth/refreshtoken")
                .maxAge(Duration.ofDays(7))
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
        return ResponseEntity.ok(Map.of(
                "jwtToken", jwtToken
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue("refreshToken") String refreshToken, HttpServletResponse response) {
        String delMessage = "";
        if (refreshToken != null)
             delMessage = jwt.deleteRefreshToken(refreshToken);
        ResponseCookie cookie = ResponseCookie.from("refreshToken","")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .build();
        response.setHeader("Set-Cookie", cookie.toString());
        return ResponseEntity.ok(Map.of(
                "message", "Logged Out successful",
                "token message", delMessage
        ));
    }



/*
    // Session-Based....
    @GetMapping("/login")
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
*/

}
