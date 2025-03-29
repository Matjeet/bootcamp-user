package com.example.bootcamp.user.domain.util;

public class StudentMessage {

    private StudentMessage(){}

    // RESPONSE MESSAGE
    public static final String CREATED_STUDENT_MESSAGE = "El estudiante se ha creado correctamente dentro de la base de datos";
    public static final String CREATED_STAFF_MESSAGE = "El miembro del equipo se ha creado correctamente en la base de datos";
    public static final String CREATED_PROFILE_MESSAGE = "El perfil del usuario ha sido creado exitosamente";

    // EXCEPTION MESSAGE
    public static final String MESSAGE_KEY = "response";
    public static final String STUDENT_EXIST = "El estudiante ya se encuentra registrado dentro de la base de datos";
    public static final String INSTITUTION_NOT_FOUND = "La institución enviada en la petición no se encuentra dentro de nuestra base de datos";
    public static final String EDUCATION_LEVEL_NOT_FOUND = "El nivel de educación enviado en la petición no se encuentra dentro de nuestra base de datos";
    public static final String DEVELOPER_ROL_NOT_FOUND = "El rol de desarrollador enviado en la petición no se encuentra dentro de nuestra base de datos";
    public static final String SOURCE_NOT_FOUND = "La fuente donde se descubrió el curso no se encuentra en la base de datos";
    public static final String CITY_NOT_FOUND = "La ciudad seleccionada no se encuentra en nuestra base de datos";
    public static final String STAFF_MEMBER_EXIST = "El miembro del equipo ya se encuentra creado en base de datos";
    public static final String STAFF_ROL_NOT_FOUND = "El rol de equipo no se encuentra en nuestra base de datos";
    public static final String EMAIL_REGISTERED_AS_A_STUDENT = "El correo que se intenta ingresar se encuentra registrado como un estudiante";
    public static final String USER_NOT_FOUND = "El usuario enviado en la petición no se encontró dentro de la base de datos";
    public static final String BADGES_NOT_FOUND = "Las insignias enviadas en la petición no se encontraron dentro de la base de datos";
    public static final String HOBBIES_NOT_FOUND = "Los pasatiempos enviados en la petición no se encontraron dentro de la base de datos";

    // VALIDATION MESSAGE
    public static final String CITY_ID_IS_NULL_MESSAGE = "El id de la ciudad es requerido";
    public static final String CITY_ID_IS_NOT_A_NUMBER_MESSAGE = "El id de la ciudad debe ser numérico";
    public static final String ADDRESS_IS_NULL_MESSAGE = "La dirección es requerida";
    public static final String ADDRESS_IS_BLANK_MESSAGE = "La dirección no puede estar vacía";
    public static final String ADDRESS_DETAILS_IS_BLANK_MESSAGE = "El detalle de la dirección no puede estar vacío";
    public static final String NAME_IS_BLANK_MESSAGE = "El nombre no puede estar vacío";
    public static final String NAME_IS_NULL_MESSAGE = "El nombre es requerido";
    public static final String LASTNAME_IS_BLANK_MESSAGE = "El apellido no puede estar vacío";
    public static final String LASTNAME_IS_NULL_MESSAGE = "El apellido es requerido";
    public static final String EMAIL_INVALID_MESSAGE = "El email proporcionado no es válido";
    public static final String EMAIL_IS_NULL_MESSAGE = "El email es requerido";
    public static final String CELLPHONE_IS_NULL_MESSAGE = "El número de celular es requerido";
    public static final String CELLPHONE_INVALID_MESSAGE = "El número de celular ingresado no es válido";
    public static final String IDENTIFICATION_IS_NULL_MESSAGE = "El número de identificación es requerido";
    public static final String IDENTIFICATION_TOO_SHORT_MESSAGE = "El número de identificación no cumple con el mínino de caracteres permitidos";
    public static final String IDENTIFICATION_INVALID_MESSAGE = "El número de identificación solo puede contener valores numéricos";
    public static final String IDENTIFICATION_TYPE_IS_NULL_MESSAGE = "El tipo de identificación es requerido";
    public static final String IDENTIFICATION_TYPE_INVALID_LENGTH_MESSAGE = "El valor del tipo de identificación no cumple con la longitud establecida";
    public static final String BIRTHDATE_IS_NOT_PAST_DATE_MESSAGE = "La fecha de nacimiento debe ser anterior a la fecha actual";
    public static final String BIRTHDATE_IS_NULL_MESSAGE = "La fecha de nacimiento es requerida";
    public static final String EDUCATION_LEVEL_IS_NULL_MESSAGE = "El nivel de educación es requerido";
    public static final String INSTITUTION_ID_IS_NULL_MESSAGE = "El id de la institución es requerido";
    public static final String INSTITUTION_ID_IS_NOT_A_NUMBER_MESSAGE = "El id de la institución debe ser numérico";
    public static final String INSTITUTION_DETAIL_IS_BLANK_MESSAGE = "Los detalles de la institución no puede estar vacío";
    public static final String DEGREE_NAME_IS_NULL_MESSAGE = "El nombre del título es requerido";
    public static final String DEGREE_NAME_IS_BLANK_MESSAGE = "El nombre del título no puede estar vacío";
    public static final String DEVELOPER_ROL_IS_NULL_MESSAGE = "El rol de desarrollador es requerido";
    public static final String COURSE_DISCOVERY_SOURCE_IS_NULL_MESSAGE = "La fuente de descubrimiento del curso es requerido";
    public static final String LOCATION_IS_NULL_MESSAGE = "La ubicación es requerida";
    public static final String STUDENT_IS_NOT_AN_ADULT = "El usuario no tiene la mayoría de edad";
    public static final String STAFF_ROLL_IS_NULL_MESSAGE = "El rol de miembro del equipo no puede ser nulo";

    // LOGGER
    public static final String MISSING_INFO = "MISSING_INFO: ";
    public static final String SOME_BADGES_NOT_FOUND = "El usuario {} intentó registrar la(s) insignia(s) {} pero no se encontraron en base de datos";
    public static final String SOME_HOBBIES_NOT_FOUND = "El usuario {} intento registrar el/los pasatiempo(s) {} pero no se encontraron en base de datos";

}
