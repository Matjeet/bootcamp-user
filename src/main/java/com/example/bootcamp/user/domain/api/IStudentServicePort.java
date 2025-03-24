package com.example.bootcamp.user.domain.api;

import com.example.bootcamp.user.domain.model.Student;
import com.example.bootcamp.user.domain.model.StudentInstitution;

public interface IStudentServicePort {
    void save (Student student, StudentInstitution studentInstitution);
}
