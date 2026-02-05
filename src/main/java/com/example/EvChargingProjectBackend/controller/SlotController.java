package com.example.EvChargingProjectBackend.controller;

import com.example.EvChargingProjectBackend.dto.SlotDto;
import com.example.EvChargingProjectBackend.dto.request.CreateSlotRequestDto;
import com.example.EvChargingProjectBackend.service.SlotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/slot")
@CrossOrigin(origins="http://localhost:5173")
public class SlotController {
    private final SlotService slotService;

    @PostMapping("/{chargerId}")
    public ResponseEntity<SlotDto> createSlot(@Valid @RequestBody CreateSlotRequestDto createSlotRequestDto,
                                              @PathVariable Long chargerId){
        return ResponseEntity.status(HttpStatus.CREATED).body(slotService.createSlot(chargerId,createSlotRequestDto));
    }

    @GetMapping("/{chargerId}/all")
    public ResponseEntity<Page<SlotDto>> getAllSlot(@PathVariable Long chargerId,
                                                    @RequestParam(defaultValue="0")int page,
                                                    @RequestParam(defaultValue="6")int size){
        return ResponseEntity.ok(slotService.getAllSlot(chargerId,page,size));
    }

    @GetMapping("/{chargerId}")
    public ResponseEntity<List<SlotDto>> getAllSlotByDate(@PathVariable Long chargerId, @RequestParam LocalDate date){
        return ResponseEntity.ok(slotService.getAllSlotByDate(chargerId,date));
    }

//    @GetMapping("/{chargerId}/today")
//    public ResponseEntity<List<SlotDto>> getAllSlotOfToday(@PathVariable Long chargerId){
//        return ResponseEntity.ok(slotService.getAllSlotOfToday(chargerId));
//    }
    @DeleteMapping("/slotId")
    public ResponseEntity<String> deleteSlot(@PathVariable Long slotId){
        slotService.deleteSlot(slotId);
        return ResponseEntity.ok("slot is deleted Succesfully");
    }
}
