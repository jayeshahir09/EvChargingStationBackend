package com.example.EvChargingProjectBackend.dto;

import com.example.EvChargingProjectBackend.entity.ChargingStation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class NearbyStation {
    private ChargingStation station;
    private double distanceKm;
}
