package com.trevis.startup.dto.service;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter @Data
public class ServiceDataUpdatePayload {
    
    @Size(min = 3, max = 50)
    private String name;

    @Size(min = 1, max = 2000)
    private String description;

    private Boolean intern;
}
