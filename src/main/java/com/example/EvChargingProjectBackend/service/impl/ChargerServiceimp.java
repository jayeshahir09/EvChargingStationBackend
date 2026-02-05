package com.example.EvChargingProjectBackend.service.impl;

import com.example.EvChargingProjectBackend.dto.ChargerDto;
import com.example.EvChargingProjectBackend.dto.ChargingStationDto;
import com.example.EvChargingProjectBackend.dto.request.CreateChargerRequestDto;
import com.example.EvChargingProjectBackend.entity.Charger;
import com.example.EvChargingProjectBackend.entity.ChargingStation;
import com.example.EvChargingProjectBackend.entity.type.ChargerType;
import com.example.EvChargingProjectBackend.repository.ChargerRepository;
import com.example.EvChargingProjectBackend.repository.ChargingStationRepository;
import com.example.EvChargingProjectBackend.service.ChargerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChargerServiceimp implements ChargerService {
    private final ChargerRepository chargerRepository;
    private final ChargingStationRepository chargingStationRepository;
    private final ModelMapper modelMapper;

    public ChargerDto createCharger(Long stationId, CreateChargerRequestDto createChargerRequestDto){
        ChargingStation chargingStation = chargingStationRepository.findById(stationId).orElseThrow(()->new IllegalArgumentException("station not found "+stationId));
        Charger charger = modelMapper.map(createChargerRequestDto,Charger.class);
        charger.setChargingStation(chargingStation);
        Charger newCharger = chargerRepository.save(charger);
        chargingStation.getChargerList().add(newCharger);
        return modelMapper.map(newCharger,ChargerDto.class);
    }

    public void deleteCharger(Long chargerId){
        chargerRepository.deleteById(chargerId);
    }

    public List<ChargerDto> getAllCharger(Long stationId){
        List<Charger> chargerList = chargerRepository.findAllByChargingStationStationId(stationId);
        return chargerList.stream().map((charger)->modelMapper.map(charger,ChargerDto.class)).toList();
    }

    public ChargerDto partialUpdateChargerDetails(Long chargerId, Map<String,Object> updates){
        Charger charger = chargerRepository.findById(chargerId).orElseThrow(()->new IllegalArgumentException("charger is not found with this id "+chargerId));
        updates.forEach((fields,value)->{
            switch(fields){
                case "chargerType":
                    charger.setChargerType(ChargerType.valueOf(value.toString()));
                    break;
                case "powerKw":
                    charger.setPowerKw((Double)value);
                    break;
                case "pricePerUnit":
                    charger.setPricePerUnit((double)value);
                    break;
                default :
                    throw new IllegalArgumentException("field not found with this name");
            }
        });
        chargerRepository.save(charger);
        return modelMapper.map(charger,ChargerDto.class);
    }
}
