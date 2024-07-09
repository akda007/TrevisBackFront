package com.trevis.startup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trevis.startup.dto.user.UserDataCreationPayload;
import com.trevis.startup.dto.user.UserDataResponse;
import com.trevis.startup.dto.user.UserDataUpdatePayload;
import com.trevis.startup.interfaces.UserDataService;
import com.trevis.startup.sessions.UserSession;

import jakarta.validation.Valid;

@RestController @RequestMapping("/user")
public class UserController {

    @Autowired
    UserSession userSession;

    @Autowired
    UserDataService userDataService;

    @PostMapping
    public ResponseEntity<UserDataResponse> addUser(@Valid @RequestBody UserDataCreationPayload body) {
       userSession.verifyAdmin();

        return ResponseEntity
                .status(201)
                .body(new UserDataResponse( userDataService.create(body) ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDataResponse> getUser(@PathVariable Long id) {
        userSession.verifyOwnUserOrAdmin(id);

        return ResponseEntity.ok(new UserDataResponse( userDataService.getById(id) ));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDataResponse> updateUser(
            @Valid @RequestBody UserDataUpdatePayload body,
            @PathVariable Long id
    ) {
        userSession.verifyOwnUserOrAdmin(id);

        return ResponseEntity.ok(new UserDataResponse( userDataService.updateUser(id, body) ));
    }
}
