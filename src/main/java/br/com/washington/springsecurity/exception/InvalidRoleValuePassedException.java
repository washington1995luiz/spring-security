package br.com.washington.springsecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidRoleValuePassedException extends ResponseStatusException {
    public InvalidRoleValuePassedException(String role) {
        super(HttpStatus.BAD_REQUEST, "Invalid role value passed: " + role);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
