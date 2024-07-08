package com.trevis.startup.example.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.startup.example.dto.ServiceDto;
import com.trevis.startup.example.dto.payloads.ServicePayload;
import com.trevis.startup.example.model.ServiceData;
import com.trevis.startup.example.model.UserData;
import com.trevis.startup.example.repositories.ServiceJPARepository;
import com.trevis.startup.example.repositories.UserJPARepository;
import com.trevis.startup.example.services.ServiceService;

@RestController
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @Autowired
    UserJPARepository userRepo;

    @Autowired
    ServiceJPARepository serviceRepo;

    @PostMapping("/service")
    public ResponseEntity<ServiceData> create(@RequestBody ServicePayload serviceData){
        Optional<UserData> userFetch = userRepo.findById(serviceData.managerId());

        if(!userFetch.isPresent()){
            return ResponseEntity.badRequest().build();
        }

        UserData user = userFetch.get();

        if (!user.getRole().getRole().equals("Manager")) {
            return ResponseEntity.badRequest().build();
        }

        ServiceData service = new ServiceData(serviceData.name(), serviceData.description(), serviceData.internal(), user);

        serviceService.add(service);
        
        return ResponseEntity.ok().body(service);
    }

    @GetMapping("/service")      //RETURNS THE MODEL WITH THE PASSWORD, CHANGE TO MANAGER LOGIN AND DEPARTMENT
    public ServiceDto getService(
            @RequestParam(defaultValue = "") String query,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        var services = serviceService.get(query, page, size);
        return new ServiceDto("Success", services);
    }

    @PutMapping("/service/{id}")
    public ResponseEntity<ServiceData> updateService(@PathVariable Long id, @RequestBody ServicePayload serviceData){
        Optional<ServiceData> serviceFetch = serviceRepo.findById(id);

        if (!serviceFetch.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        ServiceData service = serviceFetch.get();

        service.setName(serviceData.name());
        service.setDescription(serviceData.description());
        service.setIntern(serviceData.internal());

        serviceService.add(service);

        return ResponseEntity.ok().body(service);
    }
    
    @DeleteMapping("/service/{id}")
    public ResponseEntity<String> deleteService(@PathVariable Long id){
        serviceRepo.deleteById(id);
        return ResponseEntity.ok().body("Service deleted successfuly");
    }
}
