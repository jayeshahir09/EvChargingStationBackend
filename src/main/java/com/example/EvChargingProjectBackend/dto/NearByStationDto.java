package com.example.EvChargingProjectBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NearByStationDto {
    private ChargingStationDto stationDto;
    private double distanceKm;
}
