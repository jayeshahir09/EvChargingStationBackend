package com.example.EvChargingProjectBackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSlotRequestDto {
    @NotNull(message = "StartTime is required")
    private LocalDateTime startTime;
    @NotNull(message = "EndTime is required")
    private LocalDateTime endTime;
}
