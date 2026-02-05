package com.example.EvChargingProjectBackend.service;


import com.example.EvChargingProjectBackend.dto.ChargerDto;
import com.example.EvChargingProjectBackend.dto.request.CreateChargerRequestDto;

import java.util.List;
import java.util.Map;

public interface ChargerService {
    public ChargerDto createCharger(Long stationId, CreateChargerRequestDto createChargerRequestDto);
    public List<ChargerDto> getAllCharger(Long stationId);
    public ChargerDto partialUpdateChargerDetails(Long chargerId, Map<String,Object> updates);
    public void deleteCharger(Long chargerId);
}
