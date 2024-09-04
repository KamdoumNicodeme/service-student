package dev.hexa.studentservice.utils;

import lombok.Getter;

@Getter
public enum ErrorCatalog {
    STUDENT_NOT_FOUND("STUDENT_NOT_FOUND", "Student not found"),
    STUDENT_ALREADY_EXISTS("STUDENT_ALREADY_EXISTS", "Student already exists"),
    INVALID_REQUEST_BODY("INVALID_REQUEST_BODY", "Invalid request body"),
    GENERIC_ERROR("GENERIC_ERROR", "An unexpected error occurred");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
