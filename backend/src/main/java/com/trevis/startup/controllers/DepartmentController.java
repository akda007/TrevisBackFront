package com.trevis.startup.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.startup.entities.DepartmentData;
import com.trevis.startup.interfaces.DepartmentDataService;

@RestController @RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentDataService departmentService;
    
    @GetMapping
    public ResponseEntity<List<DepartmentData>> getdepartments(){
        return ResponseEntity.ok(departmentService.getAll());
    }
}
