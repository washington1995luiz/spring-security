package br.com.washington.springsecurity.exception;

import br.com.washington.springsecurity.enums.ExceptionName;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsException extends ResponseStatusException {
    public UserAlreadyExistsException() {
        super(HttpStatus.CONFLICT, ExceptionName.USER_ALREADY_EXISTS.getMessage());
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
