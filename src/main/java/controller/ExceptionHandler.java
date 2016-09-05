package main.java.controller;


public class ExceptionHandler extends Exception {
    public static final String FILE_NOT_FOUND_EXCEPTION = "Input data not found!";
    public static final String READING_FILE_EXCEPTION = "Initializing input exception!";
    public static final String INVALID_INPUT = "Invalid input!";
    public static final String NOT_FOUND_COUNTRY_CODE = "Country code is not valid!";
    public static final String NOT_FOUND_COUNTRY_NAME = "Country name is not valid!";

    public ExceptionHandler() {
    }

    public ExceptionHandler(String message) {
        super(message);
    }

    public ExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionHandler(Throwable cause) {
        super(cause);
    }
}
