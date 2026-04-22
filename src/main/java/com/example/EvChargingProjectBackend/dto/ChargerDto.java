package com.example.EvChargingProjectBackend.dto;

import com.example.EvChargingProjectBackend.entity.type.ChargerType;
import com.example.EvChargingProjectBackend.entity.type.ConnectorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargerDto {
    private Long chargerId;
    private ChargerType chargerType;
    private Double powerKw;
    private Double pricePerUnit;
    private List<ConnectorType> connectorTypes;
}
