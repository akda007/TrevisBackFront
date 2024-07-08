package com.trevis.startup.example.model;

import com.trevis.startup.example.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity @Table(name="UserData")
public class UserData extends BaseEntity {
    
    @Column(name = "Login")
    private String login;

    @Column(name = "Password")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @ManyToOne @JoinColumn(name = "IdDepartment")
    private DepartmentData department;


    public UserData() {}

    public UserData(String login, DepartmentData department, UserRole role) {
        this.login = login;
        this.department = department;
        this.role = role;
    }
}
