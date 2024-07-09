package com.trevis.startup.mocks;

import com.trevis.startup.dto.service.ServiceDataCreationPayload;
import com.trevis.startup.entities.ServiceData;
import com.trevis.startup.interfaces.ServiceDataService;
import com.trevis.startup.interfaces.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceDataServiceMock implements ServiceDataService {

    @Autowired
    UserDataService userDataService;

    private static final List<ServiceData> serviceDataList = new ArrayList<ServiceData>();
    private static Long currentId = 0L;

    @Override
    public ServiceData create(ServiceDataCreationPayload payload) {
        ServiceData serviceData = payload.toEntity();
        serviceData.setId(++currentId);
        serviceData.setManager(userDataService.getById(payload.getManagerId()));

        serviceDataList.add(serviceData);
        return serviceData;
    }

    @Override
    public List<ServiceData> get(String query, Integer pageIndex, Integer pageSize) {
        return serviceDataList
                .stream()
                .skip((long) (pageIndex - 1) * pageSize)
                .filter(x -> x.getName().contains(query))
                .toList();
    }
}
