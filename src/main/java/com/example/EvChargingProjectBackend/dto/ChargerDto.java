package com.example.EvChargingProjectBackend.dto;

import com.example.EvChargingProjectBackend.entity.type.ChargerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargerDto {
    private Long chargerId;
    private ChargerType chargerType;
    private Double powerKw;
    private Double pricePerUnit;
}
