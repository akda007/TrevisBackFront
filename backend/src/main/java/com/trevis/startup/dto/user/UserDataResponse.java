package com.trevis.startup.dto.user;

import com.trevis.startup.entities.UserData;

public class UserDataResponse {
    
    protected String username;
    protected Boolean firstLogin;
    protected Long departmentId;
    protected String role;

    public UserDataResponse(UserData user) {
        this.username = user.getUsername();
        this.firstLogin = user.getFirstLogin();
        this.departmentId = user.getDepartment().getId();
        this.role = user.getRole().toString();
    }
}
