package com.library.library.web.exceptions;

public class UserException extends AppException{
    public UserException(String message) {
        super(message);
    }

    protected UserException(String message, Throwable cause) {
        super(message, cause);
    }
}
