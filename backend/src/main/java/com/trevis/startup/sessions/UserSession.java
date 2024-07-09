package com.trevis.startup.sessions;

import com.trevis.startup.enums.UserRole;
import com.trevis.startup.exceptions.ForbiddenException;
import com.trevis.startup.exceptions.UnauthorizedException;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserSession {
    private Long id;
    private UserRole role;

    public UserSession() {
        this.id = null;
        this.role = UserRole.COLLABORATOR;
    }

    public void verifyAdmin() {
        verifyToken();
        if(!role.equals(UserRole.ADMIN)) throw new ForbiddenException();
    }

    public void verifyToken() {
        if(id == null) throw new UnauthorizedException();
    }

    public void verifyOwnUserOrAdmin(Long userId) {
        if(id == null) throw new UnauthorizedException();
        if(id.equals(userId)) return;
        if(!role.equals(UserRole.ADMIN)) throw new ForbiddenException();
    }
}
