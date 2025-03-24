package com.example.bootcamp.user.ports.driving.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import static com.example.bootcamp.user.domain.util.StudentMessage.*;

@Data
public class LocationDto {

    @NotNull(message = CITY_ID_IS_NULL_MESSAGE)
    @Positive(message = CITY_ID_IS_NOT_A_NUMBER_MESSAGE)
    private Long cityId;
    @NotBlank(message = ADDRESS_IS_BLANK_MESSAGE)
    @NotNull(message = ADDRESS_IS_NULL_MESSAGE)
    private String address;
    @NotBlank(message = ADDRESS_DETAILS_IS_BLANK_MESSAGE)
    private String addressDetails;

}
