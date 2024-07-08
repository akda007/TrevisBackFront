package com.trevis.startup.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.startup.dto.service.ServiceDataResponse;
import com.trevis.startup.interfaces.ServiceDataService;

@RestController @RequestMapping("/service")
public class ServiceController {
    
    @Autowired
    ServiceDataService serviceDataService;

    @GetMapping
    protected ResponseEntity<List<ServiceDataResponse>> getAll(@RequestParam String query, @RequestParam Integer page, @RequestParam Integer size) {
        var services = serviceDataService
            .get(query, page, size)
            .stream()
            .map(x -> new ServiceDataResponse(x))
            .toList();
        
        return ResponseEntity.ok(services);
    }
}
