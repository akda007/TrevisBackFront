package com.trevis.startup.example.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.trevis.startup.example.model.DepartmentData;
import com.trevis.startup.example.model.UserData;
import com.trevis.startup.example.model.UserRole;
import com.trevis.startup.example.repositories.UserJPARepository;
import com.trevis.startup.example.services.UserService;

public class DatabaseUserImpl implements UserService {
    
    @Autowired
    UserJPARepository userRepo;

    @Override
    public UserData create(String login, DepartmentData department, UserRole type) {
        UserData user = new UserData(login,department,type);
        UserData createdUser = userRepo.save(user);
        return createdUser;
    }

    @Override
    public void updatePassword(Long id, String newPassword) {
        var opt = userRepo.findById(id);
        if (!opt.isPresent())
        {
            return;
        }
        
        var user = opt.get();
        user.setPassword(newPassword);
        userRepo.save(user);
    }

    @Override
    public UserData get(String login) {
        Optional<UserData> user = userRepo.findByLogin(login);
        return user.get();
    }
}
