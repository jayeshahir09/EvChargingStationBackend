package com.example.EvChargingProjectBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationOwnerDto {
    private Long stationOwnerId;
    private String email;
    private String mobileNumber;
    private String name;
}
