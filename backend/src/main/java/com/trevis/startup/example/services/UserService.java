package com.trevis.startup.example.services;

import com.trevis.startup.example.model.DepartmentData;
import com.trevis.startup.example.model.UserData;
import com.trevis.startup.example.model.UserRole;

public interface UserService {
    UserData create(String login, DepartmentData department, UserRole role);
    void updatePassword(Long id, String newPassword);
    UserData get(String userName);
}
