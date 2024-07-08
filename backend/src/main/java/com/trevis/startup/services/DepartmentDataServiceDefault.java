package com.trevis.startup.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trevis.startup.entities.DepartmentData;
import com.trevis.startup.exceptions.NotFoundException;
import com.trevis.startup.interfaces.DepartmentDataService;
import com.trevis.startup.repositories.DepartmentRepository;

@Service
public class DepartmentDataServiceDefault implements DepartmentDataService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentData> getAll() {
        return departmentRepository.findAll();
    }
    
    @Override
    public DepartmentData getById(Long id) {

        var query = departmentRepository.findById(id);
        if(query.isEmpty()) throw new NotFoundException();

        return query.get();
    }
}
