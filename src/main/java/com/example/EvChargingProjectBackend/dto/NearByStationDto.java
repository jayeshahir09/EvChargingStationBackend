package com.example.EvChargingProjectBackend.dto;

import com.example.EvChargingProjectBackend.entity.type.ChargerType;
import com.example.EvChargingProjectBackend.entity.type.ConnectorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NearByStationDto {
    private ChargingStationDto stationDto;
    private double distanceKm;
    private List<ChargerType> chargerTypes;
    private List<ConnectorType> connectorTypes;
}
