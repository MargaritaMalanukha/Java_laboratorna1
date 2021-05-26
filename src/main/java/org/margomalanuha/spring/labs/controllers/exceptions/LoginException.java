package org.margomalanuha.spring.labs.controllers.exceptions;

public class LoginException extends RuntimeException {

    public LoginException(String message) {
        super(message);
    }

}
