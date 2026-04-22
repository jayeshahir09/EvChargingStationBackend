package com.example.EvChargingProjectBackend.controller;

import com.example.EvChargingProjectBackend.dto.ChargingStationDto;

import com.example.EvChargingProjectBackend.dto.NearByStationDto;
import com.example.EvChargingProjectBackend.dto.StationFilterDto;
import com.example.EvChargingProjectBackend.dto.StationOwnerDto;
import com.example.EvChargingProjectBackend.dto.request.CreateChargingStationDto;
import com.example.EvChargingProjectBackend.service.ChargingStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chargingStation")
@CrossOrigin(origins="http://localhost:5173")
public class ChargingStationController {
    private final ChargingStationService chargingStationService;
    
    @GetMapping("/{chargingStationId}")
    public ResponseEntity<ChargingStationDto> getChargingStationById(@PathVariable Long chargingStationId){
        return ResponseEntity.ok(chargingStationService.getChargingStationById(chargingStationId));
    }

    @PostMapping("/{stationOwnerId}")
    public ResponseEntity<ChargingStationDto> addChargingStaion(
            @RequestBody CreateChargingStationDto createChargingStationDto,
            @PathVariable Long stationOwnerId){
        return ResponseEntity.status(HttpStatus.CREATED).body(chargingStationService.addChargingStation(stationOwnerId,createChargingStationDto));
    }

    @PatchMapping("/{stationId}")
    public ResponseEntity<ChargingStationDto> changeChargingStationDetails(@PathVariable Long stationId,
                                                                           @RequestBody Map<String,Object> updates){
        return ResponseEntity.ok(chargingStationService.partialUpdateChargingStationDetails(stationId,updates));
    }

//    @GetMapping("/search")
//    public ResponseEntity<Page<NearByStationDto>> searchChargingStation(
//            @RequestParam String words,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "6") int size){
//
//        return ResponseEntity.ok(chargingStationService.searchChargingStation(words,page,size));
//    }

    @DeleteMapping("/{stationId}")
    public ResponseEntity<String> deleteChargingStation(@PathVariable Long stationId){
        chargingStationService.deleteChargingStation(stationId);
        return ResponseEntity.ok("Charging Station is deleted Successfully");
    }

//    @GetMapping("/{userId}/nearMe")
//    public ResponseEntity<Page<NearByStationDto>> findNearChargingStation(
//            @PathVariable Long userId,
//            @RequestParam Double latitude,
//            @RequestParam Double longitude,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "6") int size){
//        return ResponseEntity.ok(chargingStationService.findNearChargingStation(userId,latitude,longitude,page,size));
//    }

    @GetMapping("/filter")
    public ResponseEntity<Page<NearByStationDto>> filterStation(
            @ModelAttribute StationFilterDto stationFilterDto,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(defaultValue = "0") int page){
        return ResponseEntity.ok(chargingStationService.filterStation(stationFilterDto,page,size));
    }

}
