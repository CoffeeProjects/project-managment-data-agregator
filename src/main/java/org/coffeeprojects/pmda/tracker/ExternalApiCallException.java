package org.coffeeprojects.pmda.tracker;

public class ExternalApiCallException extends RuntimeException {

    public ExternalApiCallException(String message) {
        super(message);
    }
    public ExternalApiCallException(String message, Throwable e) {
        super(message, e);
    }
}