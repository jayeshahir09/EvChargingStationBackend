package com.example.EvChargingProjectBackend.service;

import com.example.EvChargingProjectBackend.dto.SlotDto;
import com.example.EvChargingProjectBackend.dto.request.CreateSlotRequestDto;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface SlotService {
    public SlotDto createSlot(Long chargerId, CreateSlotRequestDto createSlotRequestDto);
    public Page<SlotDto> getAllSlot(Long chargerId, int page, int size);
    public List<SlotDto> getAllSlotByDate(Long ChargerId, LocalDate date);
    public void deleteSlot(Long slotId);
}
