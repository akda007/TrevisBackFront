package com.trevis.startup.controllers;

import com.trevis.startup.dto.PaginatedListResponse;
import com.trevis.startup.dto.service.ServiceDataCreationPayload;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.trevis.startup.dto.service.ServiceDataResponse;
import com.trevis.startup.dto.service.ServiceDataUpdatePayload;
import com.trevis.startup.entities.UserData;
import com.trevis.startup.exceptions.ForbiddenException;
import com.trevis.startup.interfaces.ServiceDataService;
import com.trevis.startup.interfaces.UserDataService;
import com.trevis.startup.sessions.UserSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController @RequestMapping("/service")
public class ServiceController {
    
    @Autowired
    ServiceDataService serviceDataService;

    @Autowired
    UserSession userSession;

    @Autowired 
    UserDataService userDataService;


    @PostMapping
    protected ResponseEntity<ServiceDataResponse> create(@Valid @RequestBody ServiceDataCreationPayload body) {
        return ResponseEntity.status(201).body(new ServiceDataResponse( serviceDataService.create(body) ));
    }
    
    @GetMapping
    protected ResponseEntity<PaginatedListResponse<ServiceDataResponse>> getAll(
            @RequestParam(required = false) String query,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        userSession.verifyToken();

        UserData userData = userDataService.getById(userSession.getId());

        var services = serviceDataService
            .get(query != null ? query : "", page, size)
            .stream()
            .filter(x -> !x.getIntern() || x.getManager().getDepartment().getId().equals(userData.getDepartment().getId()))
            .map(x -> new ServiceDataResponse(x))
            .toList();
        
        Long totalPages =  Math.round(Math.ceil( (double)serviceDataService.countServices() / (double)size ));
        
        return ResponseEntity.ok(new PaginatedListResponse<ServiceDataResponse>(services, (long)page, totalPages));
    }

    @GetMapping("/{id}")
    protected ResponseEntity<ServiceDataResponse> getById(@PathVariable Long id) {
        userSession.verifyToken();
        return ResponseEntity.ok(new ServiceDataResponse(serviceDataService.getById(id)));
    }

    @PatchMapping("/{id}")
    protected ResponseEntity<ServiceDataResponse> updateById(@Valid @RequestBody ServiceDataUpdatePayload body, @PathVariable Long id) {
        userSession.verifyToken();

        var service = serviceDataService.getById(id);

        if(!service.getManager().getId().equals(userSession.getId())) throw new ForbiddenException();

        return ResponseEntity.ok(new ServiceDataResponse( serviceDataService.update(id, body) ));
    }

    @DeleteMapping
    protected ResponseEntity<?> deleteById(@PathVariable Long id) {
        userSession.verifyToken();

        var service = serviceDataService.getById(id);

        if(!service.getManager().getId().equals(userSession.getId())) throw new ForbiddenException();

        serviceDataService.delete(service);

        return ResponseEntity.noContent().build();
    }
}
