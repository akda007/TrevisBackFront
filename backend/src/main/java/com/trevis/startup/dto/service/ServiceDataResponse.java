package com.trevis.startup.dto.service;

import com.trevis.startup.dto.user.UserDataResponse;
import com.trevis.startup.entities.ServiceData;

public class ServiceDataResponse {
    
    protected String name;
    protected String description;
    protected Boolean intern;
    protected UserDataResponse manager;

    public ServiceDataResponse(ServiceData serviceData) {
        this.name = serviceData.getName();
        this.description = serviceData.getDescription();
        this.intern = serviceData.getIntern();
        this.manager = new UserDataResponse(serviceData.getManager());
    }
}
