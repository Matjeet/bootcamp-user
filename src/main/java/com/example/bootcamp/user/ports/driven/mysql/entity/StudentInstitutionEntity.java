package com.example.bootcamp.user.ports.driven.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Student_institution")
@AllArgsConstructor
@NoArgsConstructor
public class StudentInstitutionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "institution", referencedColumnName = "id")
    private InstitutionEntity institution;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "student", referencedColumnName = "id")
    private StudentEntity student;
    private String institutionDetail;
    private String degreeName;
}
