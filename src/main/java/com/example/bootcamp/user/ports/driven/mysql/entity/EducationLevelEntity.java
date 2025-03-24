package com.example.bootcamp.user.ports.driven.mysql.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "Education_level")
@AllArgsConstructor
@NoArgsConstructor
public class EducationLevelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
