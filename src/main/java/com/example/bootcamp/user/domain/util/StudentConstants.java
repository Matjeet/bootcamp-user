package com.example.bootcamp.user.domain.util;

public class StudentConstants {

    private StudentConstants(){}

    // GENERAL
    public static final int LEGAL_AGE = 18;
    public static final String EMPTY_STRING = "";

    // REGEX
    public static final String CELLPHONE_REGEX = "^\\+\\d{12,13}$";
    public static final String ONLY_NUMBERS_REGEX = "^\\d+$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String URL_REGEX = "^(https?|ftp):\\/\\/[^\\s\\/$.?#].[^\\s]*$";
    public static final String NOT_ONLY_BLANK_SPACE_REGEX = "^(?!\\s*$).+";

    // VALIDATION
    public static final int IDENTIFICATION_MIN_LENGTH = 7;
    public static final int IDENTIFICATION_TYPE_MIN_LENGTH = 2;
    public static final int IDENTIFICATION_TYPE_MAX_LENGTH = 4;
    public static final int DESCRIPTION_MAX_LENGTH = 500;
    public static final int INSTITUTION_DETAILS_MIN_LENGTH = 1;

    // TEST
    public static final int ONE_TIME = 1;
}
