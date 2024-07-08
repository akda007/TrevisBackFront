package com.trevis.startup.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.startup.example.dto.DepartmentDto;
import com.trevis.startup.example.services.DepartmentService;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentService deparService;
    
    @GetMapping("/department")
    public DepartmentDto getdepartments(){
        return new DepartmentDto ("Success", deparService.getAll());
    }

}
