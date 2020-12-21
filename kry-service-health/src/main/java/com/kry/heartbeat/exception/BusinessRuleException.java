package com.kry.heartbeat.exception;


import java.util.Collections;
import java.util.List;

public class BusinessRuleException extends Exception {
    public static final String INVALID_REQUEST = "Invalid request";

    private final List<String> errors;

    public BusinessRuleException(String error) {
        this(INVALID_REQUEST, error);
    }

    public BusinessRuleException(String message, String error) {
        this(message, Collections.singletonList(error));
    }

    public BusinessRuleException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
