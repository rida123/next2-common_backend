package net.claims.express.next2.security.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JWTService {

    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;
    private long expirationTime = 1800000L;

    @PostConstruct
    private void initKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048); //or 1024
        KeyPair keyPair = generator.generateKeyPair();
        privateKey = (RSAPrivateKey)keyPair.getPrivate();
        publicKey = (RSAPublicKey) keyPair.getPublic();
    }

    public String generateToken(String name, List<String> authorities) {

        String[] myAuthoritiesArray = new String[authorities.size()];
        myAuthoritiesArray = authorities.toArray(myAuthoritiesArray);

//        LocalDate tokenLife = LocalDate.
        return JWT.create()
                .withClaim("user", name)
                .withArrayClaim("authorities", myAuthoritiesArray)
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.RSA256(publicKey, privateKey));

    }

    public String validateToken(String token) throws JWTVerificationException {
        String tokenPayload_encoded = JWT.require(Algorithm.RSA256(publicKey, privateKey))
                .build()
                .verify(token)
                .getPayload();
        return new String(Base64.getDecoder().decode(tokenPayload_encoded));
    }
}
