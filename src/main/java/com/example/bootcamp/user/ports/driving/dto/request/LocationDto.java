package com.example.bootcamp.user.ports.driving.dto.request;

import lombok.Data;

@Data
public class LocationDto {

    private Long cityId;
    private String address;
    private String addressDetails;

}
