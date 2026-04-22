package com.example.EvChargingProjectBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationFilterDto {
    private String city;
    private String connectorType;
    private String chargerType;
    private String words;
}
