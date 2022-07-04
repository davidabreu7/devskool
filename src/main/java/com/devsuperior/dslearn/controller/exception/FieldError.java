package com.devsuperior.dslearn.controller.exception;

public class FieldError {

    private String fieldName;
    private String message;

    public FieldError() {
    }

    public FieldError(String error, String message) {
        this.fieldName = error;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
