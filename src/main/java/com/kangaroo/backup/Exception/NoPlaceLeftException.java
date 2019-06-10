package com.kangaroo.backup.Exception;

public class NoPlaceLeftException extends Exception {

    private static final long serialVersionUID = 342342412095804755L;

    public NoPlaceLeftException() {
        super();
    }

    public NoPlaceLeftException(String msg, Exception cause) {
        super(msg, cause);
    }

    public NoPlaceLeftException(Exception cause) {
        super(cause);
    }

    public NoPlaceLeftException(String msg) {
        super(msg);
    }
}
