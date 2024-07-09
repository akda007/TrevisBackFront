package com.trevis.startup.dto.service;

import com.trevis.startup.dto.user.UserDataResponse;
import com.trevis.startup.entities.ServiceData;

public class ServiceDataResponse {

    public Long id;
    public String name;
    public String description;
    public Boolean intern;
    public UserDataResponse manager;

    public ServiceDataResponse(ServiceData serviceData) {
        this.id = serviceData.getId();
        this.name = serviceData.getName();
        this.description = serviceData.getDescription();
        this.intern = serviceData.getIntern();
        this.manager = new UserDataResponse(serviceData.getManager());
    }
}
