package com.trevis.startup.example.services;

import java.util.List;

import com.trevis.startup.example.model.ServiceData;

public interface ServiceService {
    List<ServiceData> get(String query, int pageIndex, int pageSize);
    void add(ServiceData service);
}
