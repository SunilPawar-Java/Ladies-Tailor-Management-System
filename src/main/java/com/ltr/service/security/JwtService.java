package com.ltr.service.security;

import com.ltr.exception.RefreshTokenException;
import com.ltr.exception.UserNotFoundException;
import com.ltr.model.Users;
import com.ltr.model.security.RefreshToken;
import com.ltr.model.security.UserSecurityDetails;
import com.ltr.repository.RefreshTokenRepository;
import com.ltr.repository.UsersRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.UUID;

@Service
public class JwtService {

    @Value("${jwt.public-key-path}")
    private String publicKeyPath;
    @Value("${jwt.private-key-path}")
    private String privateKeyPath;
    private String key = " ";
    private PublicKey publicKey = null;
    private PrivateKey privateKey = null;
    private final UsersRepository usersRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public JwtService(UsersRepository usersRepository, RefreshTokenRepository refreshTokenRepository) {
        this.usersRepository = usersRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @PostConstruct
    public void init(){
        publicKey = getPublicKey();
        privateKey = getPrivateKey();
    }


    public String generateToken(UserSecurityDetails userSecurityDetails){
        return Jwts.builder()
                .subject(userSecurityDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(10, ChronoUnit.MINUTES)))
                .signWith(privateKey)
                .compact();
    }

    public Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }

    public boolean isExpired(String token){
        return !Date.from(Instant.now()).before(getClaims(token).getExpiration());
    }

    public boolean isValid(String token, String  username){
        return !isExpired(token) && extractUsername(token).equals(username);
    }

    public PublicKey getPublicKey(){
        try {
            key = Files.readString(Path.of(publicKeyPath));
            if (key.equals(" ")) {
                throw new RuntimeException("Public key File is empty");
            }
            key = key
                    .replace("-----BEGIN PUBLIC KEY-----","")
                    .replace("-----END PUBLIC KEY-----","")
                    .replaceAll("\\s","");

            publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(key)));
            if (publicKey == null)
                throw new RuntimeException("Failed to generate public key");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        try {
            key = Files.readString(Path.of(privateKeyPath));
            if (key.equals(" ")) {
                throw new RuntimeException("Private key File is empty");
            }
            key = key
                    .replace("-----BEGIN PRIVATE KEY-----","")
                    .replace("-----END PRIVATE KEY-----","")
                    .replaceAll("\\s","");

            privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key)));
            if (privateKey == null)
                throw new RuntimeException("Failed to generate private key");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return privateKey;
    }

    @Transactional
    public String generateRefreshToken(String username){
        Users user = usersRepository.findByUsername(username).orElseThrow(() ->
                new UserNotFoundException("User Not Found For Username "+ username));
        RefreshToken old = refreshTokenRepository.findByUser_Id(user.getId());
        if (old != null){
            refreshTokenRepository.delete(old);
            refreshTokenRepository.flush();
            System.out.println("token deleted");
        }
        RefreshToken newRefreshToken = RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expiration(Instant.now().plus(7, ChronoUnit.DAYS))
                    .user(user)
                    .build();
            refreshTokenRepository.save(newRefreshToken);
        return newRefreshToken.getRefreshToken();
    }

    @Transactional
    public RefreshToken verifyRefreshToken(String refreshToken){
        RefreshToken oldRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(() ->
                new RefreshTokenException("Invalid Refresh Token!"));
        if (Instant.now().isAfter(oldRefreshToken.getExpiration())){
            refreshTokenRepository.delete(oldRefreshToken);
            throw new RefreshTokenException("Refresh Token Has Expired! Login Again");
        }
        return oldRefreshToken;
    }

    public String deleteRefreshToken(String refreshToken){
        refreshTokenRepository.deleteByRefreshToken(refreshToken);
        return "Token deleted";
    }
}
