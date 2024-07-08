package com.trevis.startup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trevis.startup.dto.user.UserDataCreationPayload;
import com.trevis.startup.dto.user.UserDataUpdatePayload;
import com.trevis.startup.entities.UserData;
import com.trevis.startup.exceptions.NotFoundException;
import com.trevis.startup.interfaces.UserDataService;
import com.trevis.startup.repositories.DepartmentRepository;
import com.trevis.startup.repositories.UserRepository;

@Service
public class UserDataServiceDefault implements UserDataService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public UserData create(UserDataCreationPayload payload) {
        
        var query = departmentRepository.findById(payload.getDepartmentId());
        if(query.isEmpty()) throw new NotFoundException();

        UserData user = payload.toEntity();
        user.setDepartment(query.get());

        return userRepository.save(user);
    }

    @Override
    public UserData getById(Long id) {
        
        var query = userRepository.findById(id);
        if(query.isEmpty()) throw new NotFoundException();

        return query.get();
    }

    @Override
    public UserData getByUsername(String username) {
        
        var query = userRepository.findByUsername(username);
        if(query.isEmpty()) throw new NotFoundException();

        return query.get();
    }

    @Override
    public UserData updateUser(Long id, UserDataUpdatePayload payload) {
        
        var query = userRepository.findById(id);
        if(query.isEmpty()) throw new NotFoundException();

        UserData user = query.get();

        if(payload.getUsername() != null) user.setUsername(payload.getUsername().replaceAll("//s", ""));
        if(payload.getPassword() != null) user.setPassword(bCryptPasswordEncoder.encode(payload.getPassword()));

        return userRepository.save(user);
    }
}
