package com.example.bootcamp.user.ports.driven.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "Student")
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String lastname;
    private String cellphone;
    private String identification;
    private String identificationType;
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "education_level", referencedColumnName = "id")
    private EducationLevelEntity educationLevel;

    @ManyToOne
    @JoinColumn(name = "developer_rol", referencedColumnName = "id")
    private DeveloperRolEntity developerRol;

    @ManyToOne
    @JoinColumn(name = "source", referencedColumnName = "id")
    private SourceEntity courseDiscoverySource;
}
