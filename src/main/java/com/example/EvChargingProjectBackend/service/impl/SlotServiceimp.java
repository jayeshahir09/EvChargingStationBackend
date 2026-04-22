package com.example.EvChargingProjectBackend.service.impl;

import com.example.EvChargingProjectBackend.dto.SlotDto;
import com.example.EvChargingProjectBackend.dto.request.CreateSlotRequestDto;
import com.example.EvChargingProjectBackend.entity.Charger;
import com.example.EvChargingProjectBackend.entity.Slot;
import com.example.EvChargingProjectBackend.entity.type.SlotStatus;
import com.example.EvChargingProjectBackend.repository.ChargerRepository;
import com.example.EvChargingProjectBackend.repository.SlotRepository;
import com.example.EvChargingProjectBackend.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SlotServiceimp implements SlotService {
    private final SlotRepository slotRepository;
    private final ChargerRepository chargerRepository;
    private final ModelMapper modelMapper;

    public SlotDto createSlot(Long chargerId, CreateSlotRequestDto createSlotRequestDto){
        Charger charger = chargerRepository.findById(chargerId).orElseThrow(()->new IllegalArgumentException("Charger not found with this id "+chargerId));
        Slot slot = modelMapper.map(createSlotRequestDto,Slot.class);
        slot.setCharger(charger);
        slot.setSlotStatus(SlotStatus.AVAILABLE);
        Slot newSlot = slotRepository.save(slot);
        SlotDto slotDto = modelMapper.map(newSlot,SlotDto.class);
        return slotDto;
    }

    public Page<SlotDto> getAllSlot(Long chargerId,int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Slot> slotList = slotRepository.findAllSlotsByChargerChargerId(chargerId,pageable);
        return slotList.map(slot -> modelMapper.map(slot,SlotDto.class));
    }

    public List<SlotDto> getAllSlotByDate(Long chargerId, LocalDate date){
        if(date.equals(LocalDate.now())){
            LocalTime time = LocalTime.now();
            List<Slot> slots = slotRepository.findAllSlotsByToday(chargerId,date,time);
            return slots.stream().map((slot)->modelMapper.map(slot,SlotDto.class)).toList();
        }
        List<Slot> slotList = slotRepository.findAllSlotsByChargerAndDate(chargerId,date);
        return slotList.stream().map(slot->modelMapper.map(slot,SlotDto.class)).toList();
    }

    public void deleteSlot(Long slotId){
        slotRepository.deleteById(slotId);
    }
}
