package org.margomalanuha.spring.labs.controllers.exceptions;

public class RegistrationException extends RuntimeException {

    public RegistrationException(String message) {
        super(message);
    }

}
