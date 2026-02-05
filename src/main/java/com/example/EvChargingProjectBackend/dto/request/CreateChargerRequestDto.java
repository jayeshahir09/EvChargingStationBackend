package com.example.EvChargingProjectBackend.dto.request;

import com.example.EvChargingProjectBackend.entity.type.ChargerType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateChargerRequestDto {
    @NotNull
    private ChargerType chargerType;
    @NotNull
    private Double powerKw;
    @NotNull
    private Double pricePerUnit;
}
