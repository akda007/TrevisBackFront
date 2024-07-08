package com.trevis.startup.example.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.trevis.startup.example.model.ServiceData;
import com.trevis.startup.example.model.UserData;
import com.trevis.startup.example.model.UserRole;
import com.trevis.startup.example.services.DepartmentService;
import com.trevis.startup.example.services.ServiceService;

import jakarta.annotation.PostConstruct;

public class MockServiceService implements ServiceService {

    @Autowired
    DepartmentService departmentService;

    List<ServiceData> allServices = new ArrayList<>();

    @PostConstruct
    public void init()
    {
        UserRole userRole = new UserRole();
        userRole.setId(2l);
        userRole.setRole("Manager");
        UserData manager = new UserData("manager", departmentService.getAll().get(0),userRole);// add um gerente
        allServices.add(new ServiceData("Service 1", "Description 1", true, manager));//add servicos
        allServices.add(new ServiceData("Service 2", "Description 2", false, manager));
        allServices.add(new ServiceData("Service 3", "Description 3", true, manager));
        allServices.add(new ServiceData("Service 4", "Description 4", false, manager));
        allServices.add(new ServiceData("Service 5", "Description 5", true, manager));
        allServices.add(new ServiceData("Service 6", "Description 6", false, manager));
        allServices.add(new ServiceData("Service 7", "Description 7", true, manager));
    }

    public MockServiceService() { }

    @Override
    public void add(ServiceData service) { //add servicos
        allServices.add(service);
    }

    @Override
    public List<ServiceData> get(String query, int pageIndex, int pageSize) { // query = pesquisa; pageIndex = número da pagina ;  pageSize = quantidade de index por pagina 
        List<ServiceData> listServices = allServices.stream()
                .filter(u -> u.getName().equals(query))
                .toList(); // filtro para criar umam lista com todos os serviços que contém a query

        int pages = listServices.size() % pageSize == 0 ? listServices.size() / pageSize
                : listServices.size() / pageSize + 1; // numero total de paginas

        if (pageIndex >= pages || pageIndex < 0 || pageSize < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Input inválido!");
        } // verificação de acesso as paginas

        int initialIndex = pageIndex * pageSize; // index primerio objeto da pagina
        int finalIndex = (pageIndex + 1) * pageSize;// index primeiro objeto da proxima pagina

        List<ServiceData> returnList = new ArrayList<>();

        for (int i = initialIndex; i < finalIndex; i++) {
            returnList.add(listServices.get(i));// add para a lista de retorno

        }
        return returnList;
    }
}
