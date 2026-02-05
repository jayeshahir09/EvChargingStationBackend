package com.example.EvChargingProjectBackend.controller;

import com.example.EvChargingProjectBackend.dto.ChargerDto;
import com.example.EvChargingProjectBackend.dto.request.CreateChargerRequestDto;
import com.example.EvChargingProjectBackend.repository.ChargerRepository;
import com.example.EvChargingProjectBackend.service.ChargerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/charger")
@CrossOrigin(origins="http://localhost:5173")
public class ChargerController {
    private final ChargerService chargerService;

    @PostMapping("/{stationId}")
    public ResponseEntity<ChargerDto> createCharger(@PathVariable Long stationId,
                                                    @Valid @RequestBody CreateChargerRequestDto createChargerRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(chargerService.createCharger(stationId,createChargerRequestDto));
    }

    @GetMapping("/{stationId}")
    public ResponseEntity<List<ChargerDto>> getAllCharger(@PathVariable Long stationId){
        return ResponseEntity.ok(chargerService.getAllCharger(stationId));
    }
    @PatchMapping("/{chargerId}")
    public ResponseEntity<ChargerDto> partialUpdate(@PathVariable Long chargerId,@RequestBody Map<String,Object> updates){
        return ResponseEntity.ok(chargerService.partialUpdateChargerDetails(chargerId,updates));
    }

    @DeleteMapping("/chargerId")
    public ResponseEntity<String> deleteCharger(@PathVariable Long chargerId){
        chargerService.deleteCharger(chargerId);
        return ResponseEntity.ok("charger is deleted");
    }
    
}
