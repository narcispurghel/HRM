package com.narcispurghel.hrm.domain.dto;

public record RegisterDto(
        String username,
        String password,
        String name,
        Integer age
) {
}
