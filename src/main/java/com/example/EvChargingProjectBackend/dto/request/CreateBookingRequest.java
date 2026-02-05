package com.example.EvChargingProjectBackend.dto.request;

import com.example.EvChargingProjectBackend.dto.ChargerDto;
import com.example.EvChargingProjectBackend.dto.ChargingStationDto;
import com.example.EvChargingProjectBackend.dto.SlotDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingRequest {
    private SlotDto slotDto;
    private ChargerDto chargerDto;
    private ChargingStationDto chargingStationDto;
    private Double cost;
}
