package com.trevis.startup.dto.user;

import com.trevis.startup.entities.UserData;
import com.trevis.startup.enums.UserRole;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter @Data
public class UserDataCreationPayload {
    
    @NotEmpty
    @Size(max = 50, min = 3)
    protected String username;
    
    @NotNull
    @Positive
    protected Long departmentId;
    
    @NotNull
    @Min(value = 0)
    @Max(value = 2)
    protected Integer role;


    public UserData toEntity() {
        return new UserData(username, UserRole.intToRole(role));
    }
}
