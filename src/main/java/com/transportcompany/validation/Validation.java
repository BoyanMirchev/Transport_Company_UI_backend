package com.transportcompany.validation;

import com.transportcompany.exceptions.InvalidDataException;

public final class Validation {
    private Validation(){}

    public static void require(boolean condition, String msg) {
        if (!condition) throw new InvalidDataException(msg);
    }

    public static void notBlank(String s, String msg) {
        require(s != null && !s.isBlank(), msg);
    }

    public static void notNull(Object o, String msg) {
        require(o != null, msg);
    }
}
