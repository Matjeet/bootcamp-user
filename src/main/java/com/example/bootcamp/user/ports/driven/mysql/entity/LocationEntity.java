package com.example.bootcamp.user.ports.driven.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Location")
@AllArgsConstructor
@NoArgsConstructor
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String addressDetail;
    @ManyToOne
    @JoinColumn(name = "city", referencedColumnName = "id")
    private CityEntity city;
}
