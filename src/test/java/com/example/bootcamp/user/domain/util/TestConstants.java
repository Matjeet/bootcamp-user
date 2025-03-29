package com.example.bootcamp.user.domain.util;

import static com.example.bootcamp.user.domain.util.StudentConstants.DESCRIPTION_MAX_LENGTH;

public class TestConstants {

    private TestConstants(){}

    public static final String VALID_EMAIL = "mateo.velasquez@pragma.com.co";
    public static final String INVALID_EMAIL = "correo.invalido@.com";
    public static final Long FIRST_ID = 1L;
    public static final Integer DESCRIPTION_TOO_LONG = DESCRIPTION_MAX_LENGTH + 1;
}
