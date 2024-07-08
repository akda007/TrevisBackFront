package com.trevis.startup.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity @Table(name = "DepartmentData")
public class DepartmentData extends BaseEntity {

    @Column(name = "Name")
    private String name;


    public DepartmentData() {}

    public DepartmentData(String name) {
        this.name = name;
    }
}
