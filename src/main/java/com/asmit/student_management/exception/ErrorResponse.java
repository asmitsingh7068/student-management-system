package com.asmit.student_management.exception;

public class ErrorResponse {

    @SuppressWarnings("FieldMayBeFinal")
    private String message;
    private int status;

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    // getters
    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}