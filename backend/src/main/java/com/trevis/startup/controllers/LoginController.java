package com.trevis.startup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.startup.dto.login.LoginPayload;
import com.trevis.startup.dto.login.LoginResponse;
import com.trevis.startup.entities.UserData;
import com.trevis.startup.interfaces.LoginService;
import com.trevis.startup.interfaces.UserDataService;

import jakarta.validation.Valid;

@RestController @RequestMapping("/login")
public class LoginController {
    
    @Autowired
    LoginService loginService;

    @Autowired
    UserDataService userService;

    @PostMapping
    public ResponseEntity<LoginResponse> auth(@Valid @RequestBody LoginPayload payload) {
        
        UserData user = userService.getByUsername(payload.getUsername());
        String token = loginService.login(payload.getUsername(), payload.getPassword()); 
        
        return ResponseEntity.ok(new LoginResponse(token, user));
    }
}
