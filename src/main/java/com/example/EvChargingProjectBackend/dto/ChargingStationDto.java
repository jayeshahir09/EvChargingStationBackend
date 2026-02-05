package com.example.EvChargingProjectBackend.dto;

import com.example.EvChargingProjectBackend.entity.Charger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargingStationDto {
    private Long stationId;
    private String stationName;
    private String address; 
    private String city;
    private String state;
    private double latitude;
    private double longitude;
    private List<ChargerDto> chargerDtoList;
}
