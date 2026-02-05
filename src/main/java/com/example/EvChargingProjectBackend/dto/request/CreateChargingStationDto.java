package com.example.EvChargingProjectBackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateChargingStationDto {
    @NotBlank
    @Size(min=5,max=100,message = "station name must be between 5 to 100 characters")
    private String stationName;
    @Size(min=5,max=200,message = "address must be between 5 to 200 characters")
    private String address;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
}
