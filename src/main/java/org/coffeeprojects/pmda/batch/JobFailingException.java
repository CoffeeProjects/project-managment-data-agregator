package org.coffeeprojects.pmda.batch;

public class JobFailingException extends Exception {

    public JobFailingException(String message, Throwable e) {
        super(message, e);
    }
}
