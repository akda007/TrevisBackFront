package com.trevis.startup.example.impl;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import com.trevis.startup.example.model.UserData;
import com.trevis.startup.example.repositories.UserJPARepository;
import com.trevis.startup.example.services.AuthService;
import com.trevis.startup.example.services.PasswordService;

public class DefaultAuthImpl implements AuthService{
    
    @Autowired
    UserJPARepository userRepo;

    @Autowired
    PasswordService passService;

    static private KeyPair keyPair;

    static private void generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);

        keyPair = generator.generateKeyPair();
    }

    @Override
    public String login(String login, String password){
        Optional<UserData> userFetch = userRepo.findByLogin(login);

        if (!userFetch.isPresent()) {
            return null;
        }

        UserData userData = userFetch.get();

        if (!passService.verifyCryptography(password, userData.getPassword())) {
            return null;
        }

        if (keyPair == null) {
            try {
                generateRSAKeyPair();
            } catch(NoSuchAlgorithmException ex) {
                return null;
            }
        }

        var publicKey = (RSAPublicKey) keyPair.getPublic();
        var privateKey = (RSAPrivateKey) keyPair.getPrivate();

        String token;
        try {
            Algorithm algorithm = Algorithm.RSA512(publicKey, privateKey);
            token = JWT.create()
                .withIssuer("MyIssuer")
                .withClaim("userId", userData.getId().toString())
                .sign(algorithm);
        } catch (JWTCreationException exception){
            return null;
        }

        return token;

    }

    public DecodedJWT validate(String token) {
        if (keyPair == null) {
            try {
                generateRSAKeyPair();
            } catch(NoSuchAlgorithmException ex) {
                return null;
            }
        }

        var publicKey = (RSAPublicKey) keyPair.getPublic();
        var privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Algorithm algorithm = Algorithm.RSA512(publicKey, privateKey);
        Verification verification = JWT.require(algorithm)
            .withIssuer("MyIssuer");

        try {
            JWTVerifier verifier = verification.build();
            return verifier.verify(token);
        } catch (JWTVerificationException ex) {
            return null;
        }
    }
}
