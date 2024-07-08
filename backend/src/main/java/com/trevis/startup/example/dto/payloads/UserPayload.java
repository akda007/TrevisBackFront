package com.trevis.startup.example.dto.payloads;

public record UserPayload(
    String login,
    Long department,
    Long role
) {
}
