package com.trevis.startup.interfaces;

import com.trevis.startup.dto.user.UserDataCreationPayload;
import com.trevis.startup.dto.user.UserDataUpdatePayload;
import com.trevis.startup.entities.UserData;

public interface UserDataService {
    UserData create(UserDataCreationPayload payload);
    UserData getById(Long id);
    UserData getByUsername(String username);
    UserData updateUser(Long id, UserDataUpdatePayload payload);
}
