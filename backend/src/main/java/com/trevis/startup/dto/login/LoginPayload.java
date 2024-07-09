package com.trevis.startup.dto.login;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter @Data
public class LoginPayload {
    
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
