package com.ltr.service.security;

import com.ltr.model.security.UserSecurityDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
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

@Service
public class JwtService {

    @Value("${jwt.public-key-path}")
    private String publicKeyPath;
    @Value("${jwt.private-key-path}")
    private String privateKeyPath;
    String key = " ";
    private PublicKey publicKey = null;
    private PrivateKey privateKey = null;

    @PostConstruct
    public void init(){
        publicKey = getPublicKey();
        privateKey = getPrivateKey();
    }


    public String generateToken(UserSecurityDetails userSecurityDetails){
        return Jwts.builder()
                .subject(userSecurityDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(20, ChronoUnit.MINUTES)))
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
}
