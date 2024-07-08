package com.trevis.startup.example.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.trevis.startup.example.model.ServiceData;
import com.trevis.startup.example.repositories.ServiceJPARepository;
import com.trevis.startup.example.services.ServiceService;

public class DatabaseServiceImpl implements ServiceService{
    
    @Autowired
    ServiceJPARepository serviceRepo;

    @Override
    public void add(ServiceData service) {
        serviceRepo.save(service);
    }

    @Override
    public List<ServiceData> get(String query, int pageIndex, int pageSize) {
        var servicesList = serviceRepo
            .findAll()
            .stream()
            .filter(u -> u.getName().contains(query))
            .toList();

        int pages = 
            servicesList.size() % pageSize == 0 ? 
                servicesList.size() / pageSize : 
                servicesList.size() / pageSize + 1;
        
        if (pages == 0) {
            return new ArrayList<>();
        }
        
        pageIndex--; // Convert to base 0 form
        if (pageIndex > pages || pageIndex < 0 || pageSize < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Input inválido!");
        }

        int initialIndex = pageIndex * pageSize;
        int finalIndex = (pageIndex + 1) * pageSize;

        List<ServiceData> returnList = new ArrayList<>();

        for (int i = initialIndex; i < finalIndex; i++) {
            returnList.add(servicesList.get(i));

        }

        return returnList;
    }
}
