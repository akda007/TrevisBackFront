package com.trevis.startup.dto.login;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;

@Getter @Data
public class LoginPayload {
    
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
