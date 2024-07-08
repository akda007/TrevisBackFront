package com.trevis.startup.example.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.trevis.startup.example.model.DepartmentData;
import com.trevis.startup.example.model.UserData;
import com.trevis.startup.example.model.UserRole;
import com.trevis.startup.example.services.PasswordService;
import com.trevis.startup.example.services.UserService;

public class MockUserService implements UserService {
    
    List<UserData> allUsers = new ArrayList<>();// criando lista de usuarios
    
    Long idTest = Long.valueOf(0); 

    @Autowired
    PasswordService passService;

    @Override
    public UserData create(String login, DepartmentData department, UserRole type) {
        UserData user = new UserData(login,department,type);
        user.setId(idTest);
        user.setPassword(passService.applyCryptography("12345678"));
        allUsers.add(user);//criando o usuario
        idTest++;

        return user;
    }

    @Override
    public void updatePassword(Long id, String newPassword) {
        for (UserData userData : allUsers) {// atualisa a senha do usuario
            if (userData.getId().equals(id)) {
                userData.setPassword(newPassword);
                return;
            }
        }
    }

    @Override
    public UserData get(String userName) {
        for (UserData userData : allUsers) {// verifica se tem o usuario e retorna usuario se encontrar, se não achar retorna null
            if (userData.getLogin().equals(userName)) {
                return userData;
            }
        }
        return null;
    }
}
