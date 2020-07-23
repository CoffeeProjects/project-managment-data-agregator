package org.coffeeprojects.pmda.exception;

public class ExceptionConstant {

    private ExceptionConstant() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ERROR_PERSISTENCE = "Problem during persistence : " ;
    public static final String ERROR_API_CALL = "Problem when calling the remote API with this project : ";

    public static final String ERROR_MORE_DETAILS = " More details : ";
    public static final String ERROR_SET_ADMINISTRATOR = "Please set an administrator for this project : ";

    public static final String ERROR_STOP_PROJECT_UPDATE = "Interruption of project update => ";

    public static final String ERROR_STOP_PROJECT_RETRY = "Interruption of project retry => ";

    public static final String ERROR_UPDATE_ISSUES = "Error during update last modified issues with project : ";
    public static final String ERROR_DELETE_ISSUES = "Error during delete missing issues : ";
}
