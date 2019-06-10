package com.kangaroo.backup.Exception;

public class NotEnoughBalanceException extends Exception {

    private static final long serialVersionUID = 7284425692744709585L;

    public NotEnoughBalanceException() {
        super();
    }

    public NotEnoughBalanceException(Exception cause) {
        super(cause);
    }

    public NotEnoughBalanceException(String msg) {
        super(msg);
    }

    public NotEnoughBalanceException(String msg, Exception cause) {
        super(msg, cause);
    }

}
