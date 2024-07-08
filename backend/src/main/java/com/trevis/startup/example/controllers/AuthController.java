package com.trevis.startup.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.startup.example.dto.payloads.AuthPayload;
import com.trevis.startup.example.services.AuthService;

@RestController
public class AuthController {
    
    @Autowired
    AuthService authService;

    @PostMapping("/auth")
    public String auth(@RequestBody AuthPayload payload) {
        return authService.login(payload.login(), payload.password());
    }
}
