package com.example.EvChargingProjectBackend.service.impl;

import com.example.EvChargingProjectBackend.dto.*;
import com.example.EvChargingProjectBackend.dto.request.CreateChargingStationDto;
import com.example.EvChargingProjectBackend.entity.Charger;
import com.example.EvChargingProjectBackend.entity.ChargingStation;
import com.example.EvChargingProjectBackend.entity.StationOwner;
import com.example.EvChargingProjectBackend.entity.User;
import com.example.EvChargingProjectBackend.entity.type.ChargerType;
import com.example.EvChargingProjectBackend.entity.type.ConnectorType;
import com.example.EvChargingProjectBackend.repository.ChargerRepository;
import com.example.EvChargingProjectBackend.repository.ChargingStationRepository;
import com.example.EvChargingProjectBackend.repository.StationOwnerRepository;
import com.example.EvChargingProjectBackend.repository.UserRepository;
import com.example.EvChargingProjectBackend.service.ChargingStationService;
import com.example.EvChargingProjectBackend.specification.ChargingStationSpecification;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChargingStationServiceimp implements ChargingStationService {
    private final ChargingStationRepository chargingStationRepository;
    private final ChargerRepository chargerRepository;
    private final StationOwnerRepository stationOwnerRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public ChargingStationDto getChargingStationById(Long chargingStationId){
        ChargingStation chargingStation = chargingStationRepository.findById(chargingStationId).orElseThrow(()->new IllegalArgumentException("station not found with this id : "+chargingStationId));
        List<Charger> chargerList = chargerRepository.findChargerByChargingStationStationId(chargingStationId);
        ChargingStationDto chargingStationDto = modelMapper.map(chargingStation,ChargingStationDto.class);
        List<ChargerDto> chargerDtoList = chargerList.stream()
                .map(charger -> modelMapper.map(charger, ChargerDto.class))
                .collect(Collectors.toList());
        chargingStationDto.setChargerDtoList(chargerDtoList);
        return chargingStationDto;
    }

    public ChargingStationDto addChargingStation(Long stationOwnerId, CreateChargingStationDto createChargingStationDto){
        StationOwner stationOwner = stationOwnerRepository.findById(stationOwnerId).orElseThrow(()->new IllegalArgumentException("station owner not found with this id "+stationOwnerId));
        ChargingStation chargingStation = modelMapper.map(createChargingStationDto,ChargingStation.class);
        chargingStation.setStationOwner(stationOwner);
        ChargingStation newChargingStation = chargingStationRepository.save(chargingStation);
        stationOwner.getChargingStationList().add(newChargingStation);
        return modelMapper.map(newChargingStation,ChargingStationDto.class);
    }

    public ChargingStationDto partialUpdateChargingStationDetails(Long stationId, Map<String,Object> updates){
        ChargingStation chargingStation = chargingStationRepository.findById(stationId).orElseThrow(()->new IllegalArgumentException("station is not found with this id "+stationId));
        updates.forEach((fields,value)->{
            switch(fields){
                case "stationName":
                    chargingStation.setStationName((String)value);
                    break;
                case "address":
                    chargingStation.setAddress((String)value);
                    break;
                case "latitude":
                    chargingStation.setLatitude(Double.parseDouble(value.toString()));
                    break;
                case "longitude":
                    chargingStation.setLongitude(Double.parseDouble(value.toString()));
            }
        });
        chargingStationRepository.save(chargingStation);
        return modelMapper.map(chargingStation,ChargingStationDto.class);
    }

    public void deleteChargingStation(Long stationId){
        ChargingStation chargingStation = chargingStationRepository.findById(stationId).orElseThrow(()->new IllegalArgumentException("station not found with this id "+stationId));
        chargingStationRepository.delete(chargingStation);
    }

    public Page<ChargingStationDto> getAllChargingStation(int page,int size){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("user not found"));
        Pageable pageable = PageRequest.of(page,size);
        Page<ChargingStation> chargingStations = chargingStationRepository.findAllStationByEmail(email,pageable);
        return chargingStations.map((chargingStation)->modelMapper.map(chargingStation,ChargingStationDto.class));
    }

    public Page<NearByStationDto> searchChargingStation(String words, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<ChargingStation> chargingStations =
                chargingStationRepository.searchChargingStation(words, pageable);

        return chargingStations.map(chargingStation -> {
            ChargingStationDto stationDto =
                    modelMapper.map(chargingStation, ChargingStationDto.class);

            return NearByStationDto.builder()
                    .stationDto(stationDto)
                    .distanceKm(0.0) // search doesn’t calculate distance
                    .build();
        });
    }

//    public Page<NearByStationDto> findNearChargingStation(
//            Long userId,
//            Double userLat,
//            Double userLng,
//            int page,
//            int size
//    ) {
//        Pageable pageable = PageRequest.of(page, size);
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException(
//                        "user not found with this id " + userId));
//
//        user.setLatitude(userLat);
//        user.setLongitude(userLng);
//
//        Page<NearbyStation> stations =
//                chargingStationRepository.findNearChargingStation(userLat, userLng, pageable);
//
//        // 🔥 MAP Page<NearbyStation> → Page<NearByStationDto>
//        return stations.map(station -> {
//            ChargingStation chargingStation = station.getStation();
//            double distance = station.getDistanceKm();
//            List<Charger> chargers = chargerRepository.findAllByChargingStationStationId(chargingStation.getStationId());
//
//            List<ChargerType> chargerTypes =
//                    chargers.stream()
//                            .map(Charger::getChargerType)
//                            .distinct()
//                            .toList();
//
//            List<ConnectorType> connectorTypes =
//                    chargers.stream()
//                            .map(charger -> charger.getConnectorType())
//                            .distinct()
//                            .toList();
//
//            ChargingStationDto stationDto =
//                    modelMapper.map(chargingStation, ChargingStationDto.class);
//
//            return new NearByStationDto(stationDto, distance,chargerTypes,connectorTypes);
//        });
//    }

    public Page<NearByStationDto> filterStation(StationFilterDto stationFilterDto,int page,int size){
        Pageable pageable = PageRequest.of(page,size);

        Specification<ChargingStation> spec = Specification.where(ChargingStationSpecification.search(stationFilterDto.getWords())).
                and(ChargingStationSpecification.chargerType(stationFilterDto.getChargerType())).
                and(ChargingStationSpecification.connectorType(stationFilterDto.getConnectorType())).
                and(ChargingStationSpecification.city(stationFilterDto.getCity())); 

        Page<ChargingStation> stations = chargingStationRepository.findAll(spec,pageable);
        return stations.map(station->{
            ChargingStationDto stationDto = modelMapper.map(station,ChargingStationDto.class);
            List<Charger> chargers = chargerRepository.findAllByChargingStationStationId(station.getStationId());

            List<ChargerType> chargerTypes =
                    chargers.stream()
                            .map(Charger::getChargerType)
                            .distinct()
                            .toList();

            List<ConnectorType> connectorTypes =
                    chargers.stream()
                            .map(charger -> charger.getConnectorType())
                            .distinct()
                            .toList();
            return NearByStationDto.builder().
                    stationDto(stationDto).
                    distanceKm(0).
                    chargerTypes(chargerTypes).
                    connectorTypes(connectorTypes).
                    build();
        });

    }

//    public Page<ChargingStationDto> filterStation(StationFilterDto stationFilterDto,int page,int size){
//        Pageable pageable = PageRequest.of(page,size);
//        Specification<ChargingStation> spec = Specification.where(null);
//        if(stationFilterDto.getConnectorType()!=null){
//            spec = spec.and(hasConnectorType(stationFilterDto.getConnectorType()));
//
//        }
//        return ;
//    }

}
