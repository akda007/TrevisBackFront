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
@Table(name="ServiceData")
public class ServiceData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name="Description")
    private String description;

    @Column(name = "Internal")
    private boolean internal;

    @ManyToOne
    @JoinColumn(name = "Manager")
    private UserData manager;

    public ServiceData() {}

    public ServiceData(String name, String description, boolean internal,UserData manager) { // contrutor Servico
        this.name = name;
        this.description = description;
        this.internal = internal;
        this.manager = manager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isInternal() {
        return internal;
    }

    public void setInternal(boolean internal) {
        this.internal = internal;
    }

    public UserData getManager() {
        return manager;
    }

    public void setManager(UserData manager) {
        this.manager = manager;
    }

    
    

}
