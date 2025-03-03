package com.example.bootcamp.user.domain.util;

public class StudentConstants {

    private StudentConstants(){}

    // REGEX
    public static final String CELLPHONE_REGEX = "^\\+\\d{12,13}$";
    public static final String IDENTIFICATION_REGEX = "^\\d+$";

    // VALIDATION
    public static final int IDENTIFICATION_MIN_LENGTH = 7;
    public static final int IDENTIFICATION_TYPE_MIN_LENGTH = 2;
    public static final int IDENTIFICATION_TYPE_MAX_LENGTH = 4;
}
