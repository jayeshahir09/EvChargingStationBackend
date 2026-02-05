package com.example.EvChargingProjectBackend.dto;

import com.example.EvChargingProjectBackend.entity.type.SlotStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SlotDto {
    private Long slotId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private SlotStatus slotStatus;
}
