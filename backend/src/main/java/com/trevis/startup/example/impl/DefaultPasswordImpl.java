package com.trevis.startup.example.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import com.trevis.startup.example.services.PasswordService;

public class DefaultPasswordImpl implements PasswordService{

    @Override
    public String applyCryptography(String password) {
        return encode(password, salt());
    }

    @Override
    public boolean verifyCryptography(String password, String encodedPassword) {
        var parts = encodedPassword.split("\\$");
        var expected = encode(password, parts[1]);
        return encodedPassword.equals(expected);
    }

    @Override
    public int verifyRules(String password) {
        int passStrengh = 1;

        if (password.length() >= 8) {
            passStrengh++;
        }

        if (hasUppercase(password)) {
            passStrengh++;
        }

        if (hasLowercase(password)) {
            passStrengh++;
        }

        if (hasNumber(password)) {
            passStrengh++;
        }

        return passStrengh;
    }

    private boolean hasUppercase(String string) {
        char ch;
        for (int i=0; i < string.length(); i++) {
            ch = string.charAt(i);
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasLowercase(String string) {
        char ch;
        for (int i=0; i < string.length(); i++) {
            ch = string.charAt(i);
            if (Character.isLowerCase(ch)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasNumber(String string) {
        char ch;
        for (int i=0; i < string.length(); i++) {
            ch = string.charAt(i);
            if (Character.isDigit(ch)) {
                return true;
            }
        }
        return false;
    }

    private String encode(String value, String salt)
    {
        value += salt;
        for (int i = 0; i < 1024; i++){
            value = hash(value);
        }
        
        return value + "$" + salt;
    }

    private String hash(String originalString){
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
        byte[] bytes = originalString.getBytes(StandardCharsets.UTF_8);
        byte[] encodedhash = digest.digest(bytes);
        return toHex(encodedhash);
    }

    private String salt() {
        byte[] array = new byte[32];
        new Random().nextBytes(array);
        String generatedString = toHex(array);
        return generatedString;
    }

    private String toHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b : a)
           sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
