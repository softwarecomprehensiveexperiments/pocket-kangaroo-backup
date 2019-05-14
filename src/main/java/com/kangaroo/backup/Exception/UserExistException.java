package com.kangaroo.backup.Exception;

public class UserExistException extends Exception {
    private static final long serialVersionUID = 5822090160156859702L;

    public UserExistException() {
    }

    public UserExistException(String msg) {
        super(msg);
    }
}
