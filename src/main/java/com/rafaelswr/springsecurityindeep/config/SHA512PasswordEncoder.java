package com.rafaelswr.springsecurityindeep.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class SHA512PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return hashWithSHA512(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String hashedPassword = hashWithSHA512(rawPassword.toString());
        return encodedPassword.equals(hashedPassword);

    }

    private String hashWithSHA512(String input){
        StringBuilder result = new StringBuilder();
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte [] digested = md.digest(input.getBytes());
            for (int i = 0; i < digested.length; i++) {
                result.append(Integer.toHexString(0xFF & digested[i]));
            }
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Bad Algorithm");
        }

        return result.toString();
    }
}

