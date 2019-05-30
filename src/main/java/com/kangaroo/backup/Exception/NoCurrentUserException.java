package com.kangaroo.backup.Exception;

public class NoCurrentUserException extends Exception {

    private static final long serialVersionUID = -8215870036594072984L;

    public NoCurrentUserException() {
        super();
    }

    public NoCurrentUserException(String msg, Exception cause) {
        super(msg, cause);
    }

    public NoCurrentUserException(Exception cause) {
        super(cause);
    }

    public NoCurrentUserException(String msg) {
        super(msg);
    }
}
