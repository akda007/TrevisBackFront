package com.trevis.startup.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="UserData")
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "Login")
    private String login;

    @Column(name = "Password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "IdDepartment")
    private DepartmentData department;

    @ManyToOne
    @JoinColumn(name = "IdRole")
    private UserRole role;

    public UserData() {}

    public UserData(String login, DepartmentData department, UserRole role) {// construtor Usuario
        this.login = login;
        this.department = department;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DepartmentData getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentData department) {
        this.department = department;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    

    
}
