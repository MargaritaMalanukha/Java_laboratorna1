package org.margomalanuha.spring.labs.service.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WrongEmailOrPasswordException extends RuntimeException {

    public WrongEmailOrPasswordException(String message) {
        super(message);
    }

}
