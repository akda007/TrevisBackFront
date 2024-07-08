package com.trevis.startup.example.services;

public interface PasswordService {
    String applyCryptography(String password);
    boolean verifyCryptography(String password, String encryptedPassword);
    int verifyRules(String password);
}
