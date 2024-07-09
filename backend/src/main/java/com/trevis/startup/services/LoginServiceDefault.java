package com.trevis.startup.services;

import com.trevis.startup.entities.UserData;
import com.trevis.startup.exceptions.UnauthorizedException;
import com.trevis.startup.interfaces.JwtTokenManager;
import com.trevis.startup.interfaces.LoginService;
import com.trevis.startup.interfaces.UserDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceDefault implements LoginService {

    @Autowired
    UserDataService userDataService;

    @Autowired
    JwtTokenManager jwtTokenManager;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String login(String username, String password) {

        UserData userData = userDataService.getByUsername(username);

        if(bCryptPasswordEncoder.matches(password, userData.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", userData.getRole());

            return jwtTokenManager.buildToken(claims, userData.getUsername(), userData.getId());
        }

        throw new UnauthorizedException();
    }
}
