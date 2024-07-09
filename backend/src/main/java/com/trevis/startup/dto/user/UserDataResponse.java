package com.trevis.startup.dto.user;

import com.trevis.startup.entities.DepartmentData;
import com.trevis.startup.entities.UserData;

public class UserDataResponse {

    public Long id;
    public String username;
    public Boolean firstLogin;
    public DepartmentData department;
    public String role;

    public UserDataResponse(UserData user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstLogin = user.getFirstLogin();
        this.department = user.getDepartment();
        this.role = user.getRole().toString();
    }
}
