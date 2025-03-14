package br.com.washington.springsecurity.exception;

import br.com.washington.springsecurity.enums.ExceptionName;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, ExceptionName.USER_NOT_FOUND.getMessage());
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
