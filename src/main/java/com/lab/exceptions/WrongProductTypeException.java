package com.lab.exceptions;

public class WrongProductTypeException extends RuntimeException {

    public WrongProductTypeException(String title) {
        super("Product with title " + title + " does not exist.");
    }

}
