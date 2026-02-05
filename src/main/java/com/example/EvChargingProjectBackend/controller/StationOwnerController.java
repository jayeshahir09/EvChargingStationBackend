package com.example.EvChargingProjectBackend.controller;

import com.example.EvChargingProjectBackend.dto.ChargingStationDto;
import com.example.EvChargingProjectBackend.dto.StationOwnerDto;
import com.example.EvChargingProjectBackend.dto.request.CreateOwnerRequestDto;
import com.example.EvChargingProjectBackend.dto.request.LoginOwnerRequestDto;
import com.example.EvChargingProjectBackend.entity.ChargingStation;
import com.example.EvChargingProjectBackend.service.ChargingStationService;
import com.example.EvChargingProjectBackend.service.StationOwnerService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stationOwner")
@CrossOrigin(origins="http://localhost:5173")
public class StationOwnerController {
    private final StationOwnerService stationOwnerService;
    private final ChargingStationService chargingStationService;

    @PostMapping("/register")
    public ResponseEntity<StationOwnerDto> registerOwner(@Valid @RequestBody CreateOwnerRequestDto createOwnerRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(stationOwnerService.registerOwner(createOwnerRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<StationOwnerDto> login(@Valid @RequestBody LoginOwnerRequestDto loginOwnerRequestDto){
        return ResponseEntity.ok(stationOwnerService.loginOwner(loginOwnerRequestDto));
    }

    @GetMapping("/{stationOwnerId}")
    public ResponseEntity<Page<ChargingStationDto>> getAllChargingStation(
            @PathVariable Long stationOwnerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size){
        return ResponseEntity.ok(chargingStationService.getAllChargingStation(stationOwnerId,page,size));
    }


}
