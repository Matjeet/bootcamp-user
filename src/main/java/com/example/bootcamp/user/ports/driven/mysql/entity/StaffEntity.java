package com.example.bootcamp.user.ports.driven.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Staff")
@AllArgsConstructor
@NoArgsConstructor
public class StaffEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String email;

    @ManyToOne
    @JoinColumn(name = "developer_rol", referencedColumnName = "id")
    private DeveloperRolEntity developerRol;

    @ManyToOne
    @JoinColumn(name = "staff_rol", referencedColumnName = "id")
    private StaffRolEntity staffRol;
}
