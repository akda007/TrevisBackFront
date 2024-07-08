package com.trevis.startup.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.startup.example.dto.payloads.PasswordPayload;
import com.trevis.startup.example.dto.payloads.UserPayload;
import com.trevis.startup.example.model.DepartmentData;
import com.trevis.startup.example.model.UserData;
import com.trevis.startup.example.model.UserRole;
import com.trevis.startup.example.services.DepartmentService;
import com.trevis.startup.example.services.PasswordService;
import com.trevis.startup.example.services.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    PasswordService passService;
    
    @PostMapping("/user")
    public ResponseEntity<UserData> create(@RequestBody UserPayload userData){
        DepartmentData department = departmentService.getById(userData.department());

        UserRole userRole; 

        UserData user;

        switch (userData.role().intValue()){
            case 0 -> {
                userRole = new UserRole();
                userRole.setId(1l);
                userRole.setRole("Administrator");
                user = userService.create(userData.login(), department, userRole);
            }
            case 1 -> {
                userRole = new UserRole();
                userRole.setId(2l);
                userRole.setRole("Manager");
                user = userService.create(userData.login(), department, userRole);
            }
            case 2 -> {
                userRole = new UserRole();
                userRole.setId(3l);
                userRole.setRole("Colaborator");
                user = userService.create(userData.login(), department, userRole);
            }
            default -> {
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.ok().body(user);
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<String> updatePassword(@RequestBody PasswordPayload newPassword, @PathVariable Long id){
        if (passService.verifyRules(newPassword.password()) != 5) {
            return ResponseEntity.badRequest().build();
        } else {
            userService.updatePassword(id, passService.applyCryptography(newPassword.password()));
            return ResponseEntity.ok().body("Show de bola");
        }
    }

}
