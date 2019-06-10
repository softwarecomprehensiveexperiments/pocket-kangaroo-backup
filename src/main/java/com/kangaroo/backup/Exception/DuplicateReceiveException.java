package com.kangaroo.backup.Exception;

public class DuplicateReceiveException extends Exception {

    private static final long serialVersionUID = 425141910916501001L;

    public DuplicateReceiveException() {
        super();
    }

    public DuplicateReceiveException(String msg, Exception cause) {
        super(msg, cause);
    }

    public DuplicateReceiveException(Exception cause) {
        super(cause);
    }

    public DuplicateReceiveException(String msg) {
        super(msg);
    }

}
