package com.trevis.startup.controllers;

import java.util.List;

import com.trevis.startup.dto.service.ServiceDataCreationPayload;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trevis.startup.dto.service.ServiceDataResponse;
import com.trevis.startup.interfaces.ServiceDataService;

@RestController @RequestMapping("/service")
public class ServiceController {
    
    @Autowired
    ServiceDataService serviceDataService;

    
    @GetMapping
    protected ResponseEntity<List<ServiceDataResponse>> getAll(
            @RequestParam(required = false) String query,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        var services = serviceDataService
            .get(query != null ? query : "", page, size)
            .stream()
            .map(x -> new ServiceDataResponse(x))
            .toList();
        
        return ResponseEntity.ok(services);
    }

    @PostMapping
    protected ResponseEntity<ServiceDataResponse> create(@Valid @RequestBody ServiceDataCreationPayload body) {
        return ResponseEntity.status(201).body(new ServiceDataResponse( serviceDataService.create(body) ));
    }
}
