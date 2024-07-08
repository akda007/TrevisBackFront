package com.trevis.startup.interfaces;

import java.util.List;

import com.trevis.startup.entities.DepartmentData;

public interface DepartmentDataService {
    List<DepartmentData> getAll();
    DepartmentData getById(Long id);
}
