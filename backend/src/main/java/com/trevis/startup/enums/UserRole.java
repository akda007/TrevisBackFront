package com.trevis.startup.enums;

import java.security.InvalidParameterException;

public enum UserRole {
    COLLABORATOR,
    MANAGER, 
    ADMIN;

    public static UserRole intToRole(Integer input) {
        switch (input) {
            case 0:
                return COLLABORATOR;
            case 1:
                return MANAGER;
            case 2:
                return ADMIN;
            default:
                throw new InvalidParameterException();
        }
    }

    public static Integer roleToInt(UserRole input) {
        switch (input) {
            case COLLABORATOR:
                return 0;
            case MANAGER:
                return 1;
            case ADMIN:
                return 2;
            default:
                throw new InvalidParameterException();
        }
    }
}
