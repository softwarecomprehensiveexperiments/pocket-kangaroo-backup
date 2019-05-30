package com.kangaroo.backup.Exception;

/**
 * 用户已存在的异常类
 */
public class UserExistException extends Exception {

    private static final long serialVersionUID = 5822090160156859702L;

    public UserExistException() {
        super();
    }

    public UserExistException(String msg, Exception cause) {
        super(msg, cause);
    }

    public UserExistException(Exception cause) {
        super(cause);
    }

    public UserExistException(String msg) {
        super(msg);
    }
}
