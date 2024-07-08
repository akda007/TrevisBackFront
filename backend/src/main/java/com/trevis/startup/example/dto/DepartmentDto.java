package com.trevis.startup.example.dto;

import java.util.List;

import com.trevis.startup.example.model.DepartmentData;

public record DepartmentDto (
    String message, 
    List<DepartmentData> data
){}
