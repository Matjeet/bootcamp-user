package com.example.bootcamp.user.domain.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.bootcamp.user.domain.util.StudentConstants.*;
import static java.util.Objects.isNull;

public class Validations {

    private Validations(){}

    public static boolean isValidEmail(String email){
        if(isNull(email)){
            return false;
        }

        Pattern emailPattern =  Pattern.compile(EMAIL_REGEX);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidCellphone(String cellphone){
        if(isNull(cellphone)){
            return false;
        }

        Pattern emailPattern =  Pattern.compile(CELLPHONE_REGEX);
        Matcher matcher = emailPattern.matcher(cellphone);
        return matcher.matches();
    }

    public static boolean isValidIdentification(String identification){
        if(isNull(identification)){
            return false;
        }

        Pattern emailPattern =  Pattern.compile(IDENTIFICATION_REGEX);
        Matcher matcher = emailPattern.matcher(identification);
        return matcher.matches();
    }

    public static boolean isAdult(LocalDate birthdate){
        if(isNull(birthdate)){
            return false;
        }

        return ChronoUnit.YEARS.between(birthdate, LocalDate.now()) >= LEGAL_AGE;
    }

    public static boolean isValidDescription(String description){
        return description.length() >= DESCRIPTION_MAX_LENGTH;
    }
}
