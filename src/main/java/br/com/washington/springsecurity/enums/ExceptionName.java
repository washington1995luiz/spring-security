package br.com.washington.springsecurity.enums;

import lombok.Getter;

@Getter
public enum ExceptionName {

    USER_NOT_FOUND("User not found!"),
    INVALID_CREDENTIALS("Invalid credentials!"),
    USER_ALREADY_EXISTS("User already exists!");

    private final String message;

    ExceptionName(String s) {
        this.message = s;
    }

}
