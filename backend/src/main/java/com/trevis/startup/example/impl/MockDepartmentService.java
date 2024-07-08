package com.trevis.startup.example.impl;

import java.util.ArrayList;
import java.util.List;

import com.trevis.startup.example.model.DepartmentData;
import com.trevis.startup.example.services.DepartmentService;

public class MockDepartmentService implements DepartmentService {
    
    List<DepartmentData> allDepartments = new ArrayList<>();
    
    public MockDepartmentService(){ // add departamentos no mock
        allDepartments.add(new DepartmentData("Engenharia"));
        allDepartments.add(new DepartmentData("BDO"));
        allDepartments.add(new DepartmentData("ICO"));
    }
    
    @Override
    public List<DepartmentData> getAll() { // pegando todos os departamentos
        return allDepartments;
    }

    @Override
    public DepartmentData getById(Long id) {
        for(var department : allDepartments){
            if(department.getId().equals(id)) {
                return department;
            }
        }

        return null;
    }
}
