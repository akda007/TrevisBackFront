package com.trevis.startup.dto.login;

import com.trevis.startup.dto.user.UserDataResponse;
import com.trevis.startup.entities.UserData;

public class LoginResponse {
    
    public String token;
    public UserDataResponse user;

    public LoginResponse(String token, UserData user) {
        this.token = token;
        this.user = new UserDataResponse(user);
    }
}
