package org.coffeeprojects.pmda.exception;

public class InvalidDataException extends RuntimeException {

    public InvalidDataException(String message) {
        super(message);
    }
    public InvalidDataException(String message, Throwable e) {
        super(message, e);
    }
}
