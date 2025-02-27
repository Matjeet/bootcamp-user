package com.example.bootcamp.user.domain.util;

public class StudentMessage {

    private StudentMessage(){}

    // RESPONSE MESSAGE
    public static final String CREATED_MESSAGE = "El estudiante se ha creado correctamente dentro de la base de datos";

    // EXCEPTION MESSAGE
    public static final String ERROR = "Error: ";
    public static final String STUDENT_EXIST = "El estudiante ya se encuentra registrado dentro de la base de datos";
}
