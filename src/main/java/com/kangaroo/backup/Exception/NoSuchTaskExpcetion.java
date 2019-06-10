package com.kangaroo.backup.Exception;

public class NoSuchTaskExpcetion extends Exception {

    private static final long serialVersionUID = -447801656590100381L;

    public NoSuchTaskExpcetion() {
        super();
    }

    public NoSuchTaskExpcetion(String msg, Exception cause) {
        super(msg, cause);
    }

    public NoSuchTaskExpcetion(Exception cause) {
        super(cause);
    }

    public NoSuchTaskExpcetion(String msg) {
        super(msg);
    }
}
