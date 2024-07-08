package com.trevis.startup.example.dto;

import java.util.List;

import com.trevis.startup.example.model.ServiceData;

public record ServiceDto (
    String message,
    List<ServiceData> data
){
}
