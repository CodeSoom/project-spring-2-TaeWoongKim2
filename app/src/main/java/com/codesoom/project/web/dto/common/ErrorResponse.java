package com.codesoom.project.web.dto.common;

/**
 * 에러 응답 DTO
 */
public class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
