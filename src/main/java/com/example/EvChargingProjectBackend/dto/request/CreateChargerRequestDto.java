package com.example.EvChargingProjectBackend.dto.request;

import com.example.EvChargingProjectBackend.entity.type.ChargerType;
import com.example.EvChargingProjectBackend.entity.type.ConnectorType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @NotNull
    private List<ConnectorType> connectorTypes;
}
