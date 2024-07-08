package com.trevis.startup.example.dto.payloads;

public record ServicePayload(
    String name,
    String description,
    Boolean internal,
    Long managerId
) {
}
