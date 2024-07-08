package com.trevis.startup.sessions;

import com.trevis.startup.exceptions.ForbiddenException;
import com.trevis.startup.exceptions.UnauthorizedException;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserSession {
    private Long id;
    private Boolean admin;

    public UserSession() {
        this.id = null;
        this.admin = false;
    }

    public void verifyAdmin() {
        verifyToken();
        if(!admin) throw new ForbiddenException();
    }

    public void verifyToken() {
        if(id == null) throw new UnauthorizedException();
    }

    public void verifyOwnUserOrAdmin(Long userId) {
        if(id == null) throw new UnauthorizedException();
        if(id.equals(userId)) return;
        if(!admin) throw new ForbiddenException();
    }
}
