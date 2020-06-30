package com.tsyhankov.tasktracker.controller.dto;

import lombok.Getter;

@Getter
public class AuthenticationResponseDto {
    private final String jwt;

    public AuthenticationResponseDto(String jwt) {
        this.jwt = jwt;
    }
}
