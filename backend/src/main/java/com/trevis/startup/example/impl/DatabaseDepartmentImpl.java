package com.trevis.startup.example.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.trevis.startup.example.model.DepartmentData;
import com.trevis.startup.example.repositories.DepartmentJPARepository;
import com.trevis.startup.example.services.DepartmentService;

public class DatabaseDepartmentImpl implements DepartmentService{
    
    @Autowired
    DepartmentJPARepository departmentRepo;

    @Override
    public List<DepartmentData> getAll() {
        return departmentRepo.findAll();
    }

    @Override
    public DepartmentData getById(Long id) {
        var department = departmentRepo.findById(id);

        if (department.isEmpty()) {
            return null;
        }

        return department.get();
    }
}
