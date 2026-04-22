package com.example.EvChargingProjectBackend.service;

import com.example.EvChargingProjectBackend.dto.ChargingStationDto;
import com.example.EvChargingProjectBackend.dto.NearByStationDto;
import com.example.EvChargingProjectBackend.dto.NearbyStation;
import com.example.EvChargingProjectBackend.dto.StationFilterDto;
import com.example.EvChargingProjectBackend.dto.request.CreateChargingStationDto;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface ChargingStationService {
    public ChargingStationDto getChargingStationById(Long chargingStationId);
    public ChargingStationDto addChargingStation(Long StationOwnerId, CreateChargingStationDto createChargingStationDto);
    public ChargingStationDto partialUpdateChargingStationDetails(Long stationId, Map<String,Object> updates);
    public void deleteChargingStation(Long stationId);
    public Page<ChargingStationDto> getAllChargingStation(int page,int size);
    public Page<NearByStationDto> searchChargingStation(String words,int page,int size);
//    public Page<NearByStationDto> findNearChargingStation(Long userId, Double latitude, Double longitude, int page, int size);
    public Page<NearByStationDto> filterStation(StationFilterDto stationFilterDto,int page,int size);
}
