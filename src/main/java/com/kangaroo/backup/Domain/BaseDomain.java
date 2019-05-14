package com.kangaroo.backup.Domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public abstract class BaseDomain implements Serializable {

    public abstract int getId();

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
