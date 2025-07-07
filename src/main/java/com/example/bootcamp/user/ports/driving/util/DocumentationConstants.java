package com.example.bootcamp.user.ports.driving.util;

public class DocumentationConstants {

    private DocumentationConstants() {
    }

    public static final String USER_TAG = "User controller";
    public static final String USER_DESCRIPTION = "Controlador encargado de manejar las acciones sobre los usuarios y sus perfiles";

    public static final String CREATE_STUDENT_SUMMARY = "Crear estudiante";
    public static final String CREATE_STUDENT_DESCRIPTION = "Permite crear usuarios con el rol de estudiantes dentro de la base de datos SQL";
    public static final String CREATE_STAFF_SUMMARY = "Crear integrante del staff";
    public static final String CREATE_STAFF_DESCRIPTION = "Permite crear un miembro del staff dentro de la base de datos SQL";
    public static final String CREATE_PROFILE_SUMMARY = "Crear perfil";
    public static final String CREATE_PROFILE_DESCRIPTION = "Permite crear un perfil de un usuario estudiante o staff dentro de la base de datos NoSQL";
    public static final String GET_PROFILE_SUMMARY = "Obtener un perfil por email";
    public static final String GET_PROFILE_DESCRIPTION = "Permite obtener un perfil de usuario por el email de la base de datos NoSQL";
    public static final String UPDATE_PROFILE_SUMMARY = "Actualizar el perfil";
    public static final String UPDATE_PROFILE_DESCRIPTION = "Permite actualizar la información del perfil para un usuario dentro de la base de datos NoSQL";
    public static final String DELETE_PROFILE_SUMMARY = "Eliminar un perfil";
    public static final String DELETE_PROFILE_DESCRIPTION = "Permite eliminar un perfil de usuario de la base de datos NoSQL";

    public static final String CREATED_201_DESCRIPTION = "Se ha creado exitosamente";
    public static final String BAD_REQUEST_400_DESCRIPTION = "La petición no es válida";
    public static final String INTERNAL_ERROR_500_DESCRIPTION = "Ha pasado un error inesperado dentro del flujo";
    public static final String INTERNAL_ERROR_500_UPDATE_PROFILE_DESCRIPTION = "Ha ocurrido un error al intentar actualizar el perfil en la base de datos NoSQL";

    public static final String HTTP_CREATED_CODE = "201";
    public static final String HTTP_BAD_REQUEST_CODE = "400";
    public static final String HTTP_INTERNAL_ERROR_CODE = "500";
}
