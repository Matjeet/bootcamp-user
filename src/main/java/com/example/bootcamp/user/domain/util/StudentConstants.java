package com.example.bootcamp.user.domain.util;

public class StudentConstants {

    private StudentConstants(){}

    // GENERAL
    public static final int LEGAL_AGE = 18;

    // REGEX
    public static final String CELLPHONE_REGEX = "^\\+\\d{12,13}$";
    public static final String IDENTIFICATION_REGEX = "^\\d+$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    // VALIDATION
    public static final int IDENTIFICATION_MIN_LENGTH = 7;
    public static final int IDENTIFICATION_TYPE_MIN_LENGTH = 2;
    public static final int IDENTIFICATION_TYPE_MAX_LENGTH = 4;

    // TEST
    public static final int ONE_TIME = 1;
}
