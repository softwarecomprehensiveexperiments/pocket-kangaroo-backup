package com.kangaroo.backup.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class QueryResult<T> implements Serializable {

    private static final long serialVersionUID = -940786331195227696L;

    @JsonProperty(value = "is_success")
    private boolean success;

    @JsonProperty(value = "error_code")
    private int errorCode;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "result")
    private T t;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
