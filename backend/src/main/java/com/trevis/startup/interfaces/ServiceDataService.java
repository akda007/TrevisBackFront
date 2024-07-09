package com.trevis.startup.interfaces;

import java.util.List;

import com.trevis.startup.dto.service.ServiceDataCreationPayload;
import com.trevis.startup.dto.service.ServiceDataUpdatePayload;
import com.trevis.startup.entities.ServiceData;

public interface ServiceDataService {
    ServiceData create(ServiceDataCreationPayload payload);
    ServiceData getById(Long id);
    List<ServiceData> get(String query, Integer pageIndex, Integer pageSize);
    ServiceData update(Long id, ServiceDataUpdatePayload payload);
    void delete(ServiceData serviceData);
}
