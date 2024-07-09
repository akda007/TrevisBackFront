package com.trevis.startup.enums;

import java.security.InvalidParameterException;

public enum UserRole {
    COLLABORATOR,
    MANAGER, 
    ADMIN;

    public static UserRole intToRole(Integer input) {
        return switch (input) {
            case 0 -> COLLABORATOR;
            case 1 -> MANAGER;
            case 2 -> ADMIN;
            default -> throw new InvalidParameterException();
        };
    }

    public static Integer roleToInt(UserRole input) {
        return switch (input) {
            case COLLABORATOR -> 0;
            case MANAGER -> 1;
            case ADMIN -> 2;
        };
    }
}
