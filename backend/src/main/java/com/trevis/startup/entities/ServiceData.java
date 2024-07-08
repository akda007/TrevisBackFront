package com.trevis.startup.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity @Table(name="ServiceData") 
public class ServiceData extends BaseEntity {

    @Column(name = "Name")
    private String name;

    @Column(name="Description")
    private String description;

    @Column(name = "Intern")
    private Boolean intern;

    @ManyToOne @JoinColumn(name = "Manager")
    private UserData manager;


    public ServiceData() {}

    public ServiceData(String name, String description, Boolean intern) {
        this.name = name;
        this.description = description;
        this.intern = intern;
    }
}
