package com.trevis.startup.dto.service;

import com.trevis.startup.entities.ServiceData;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter @Data
public class ServiceDataCreationPayload {

    @NotEmpty
    @Size(min = 3, max = 50)
    private String name;

    @NotEmpty
    @Size(min = 1, max = 2000)
    private String description;

    @NotNull
    private Boolean intern;


    public ServiceData toEntity() {
        return new ServiceData(name, description, intern);
    }
}
