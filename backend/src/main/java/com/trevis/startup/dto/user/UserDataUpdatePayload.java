package com.trevis.startup.dto.user;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter @Data
public class UserDataUpdatePayload {
    
    @Size(max = 50, min = 3)
    protected String username;
    
    @Positive
    protected Long departmentId;
    
    @Min(value = 0)
    @Max(value = 2)
    protected Integer role;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Password is too week")
    protected String password;
}
