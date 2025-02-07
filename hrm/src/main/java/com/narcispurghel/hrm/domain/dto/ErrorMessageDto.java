package com.narcispurghel.hrm.domain.dto;

import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

public class ErrorMessageDto {
    private HttpStatusCode httpStatusCode;
    private String message;
    private LocalDateTime createdAt;

    public ErrorMessageDto() {
        this.createdAt = LocalDateTime.now();
    }

    public ErrorMessageDto(HttpStatusCode httpStatusCode,
                           String message) {
        this.message = message;
        this.httpStatusCode = httpStatusCode;
        this.createdAt = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(HttpStatusCode httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
