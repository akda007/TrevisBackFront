package com.trevis.startup.example;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.trevis.startup.example.impl.DatabaseDepartmentImpl;
import com.trevis.startup.example.impl.DatabaseServiceImpl;
import com.trevis.startup.example.impl.DatabaseUserImpl;
import com.trevis.startup.example.impl.DefaultPasswordImpl;
import com.trevis.startup.example.services.DepartmentService;
import com.trevis.startup.example.services.PasswordService;
import com.trevis.startup.example.services.ServiceService;
import com.trevis.startup.example.services.UserService;


@Configuration
public class DependenciesConfiguration {
    @Bean
    @Scope("singleton")
    public DepartmentService departmentService() {
        return new DatabaseDepartmentImpl();
    }
    @Bean
    @Scope("singleton")
    public ServiceService serviceService() {
        return new DatabaseServiceImpl();
    }

    @Bean
    @Scope("singleton")
    public UserService userService() {
        return new DatabaseUserImpl();
    }

    @Bean
    @Scope("singleton")
    public PasswordService passService(){
        return new DefaultPasswordImpl();
    }

}