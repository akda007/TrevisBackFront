package com.trevis.startup.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.trevis.startup.dto.service.ServiceDataCreationPayload;
import com.trevis.startup.entities.ServiceData;
import com.trevis.startup.exceptions.NotFoundException;
import com.trevis.startup.interfaces.ServiceDataService;
import com.trevis.startup.repositories.ServicePaginationRepository;
import com.trevis.startup.repositories.ServiceRepository;
import com.trevis.startup.repositories.UserRepository;

public class ServiceDataServiceDefault implements ServiceDataService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServicePaginationRepository servicePaginationRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public ServiceData create(ServiceDataCreationPayload payload) {

        var query = userRepository.findById(payload.getManagerId());
        if(query.isEmpty()) throw new NotFoundException();

        ServiceData serviceData = payload.toEntity();
        serviceData.setManager(query.get());

        return serviceRepository.save(serviceData);
    }

    @Override
    public List<ServiceData> get(String query, Integer pageIndex, Integer pageSize) {
        return servicePaginationRepository.findByName(query, PageRequest.of(pageIndex - 1, pageSize));
    }
    
}
