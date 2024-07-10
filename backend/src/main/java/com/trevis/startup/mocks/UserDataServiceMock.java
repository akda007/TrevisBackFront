package com.trevis.startup.mocks;

import com.trevis.startup.dto.user.UserDataCreationPayload;
import com.trevis.startup.dto.user.UserDataUpdatePayload;
import com.trevis.startup.entities.UserData;
import com.trevis.startup.exceptions.NotFoundException;
import com.trevis.startup.interfaces.DepartmentDataService;
import com.trevis.startup.interfaces.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDataServiceMock implements UserDataService {

    @Autowired
    DepartmentDataService departmentDataService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final List<UserData> userDataList = new ArrayList<UserData>();
    private static Long currentId = 0L;

    @Override
    public UserData create(UserDataCreationPayload payload) {
        UserData userData = payload.toEntity();
        userData.setId(++currentId);
        userData.setDepartment(departmentDataService.getById(payload.getDepartmentId()));
        userDataList.add(userData);
        return userData;
    }

    @Override
    public UserData getById(Long id) {
        var query = userDataList
                .stream()
                .filter(department -> department.getId().equals(id))
                .findFirst();

        if(query.isEmpty()) throw new NotFoundException();

        return query.get();
    }

    @Override
    public UserData getByUsername(String username) {
        var query = userDataList
                .stream()
                .filter(department -> department.getUsername().equals(username))
                .findFirst();

        if(query.isEmpty()) throw new NotFoundException();

        return query.get();
    }

    @Override
    public UserData updateUser(Long id, UserDataUpdatePayload payload) {

        UserData user = getById(id);
        user.setFirstLogin(false);

        if(payload.getUsername() != null) user.setUsername(payload.getUsername().replaceAll("//s", ""));
        if(payload.getPassword() != null) user.setPassword(bCryptPasswordEncoder.encode(payload.getPassword()));

        return user;
    }
}
