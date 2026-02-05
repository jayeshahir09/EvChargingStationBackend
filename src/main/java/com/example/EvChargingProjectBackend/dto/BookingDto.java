package com.example.EvChargingProjectBackend.dto;

import com.example.EvChargingProjectBackend.entity.type.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private Long bookingId;
    private Double cost;
    private BookingStatus bookingStatus;
    private SlotDto slotDto;
    private ChargerDto chargerDto;
    private ChargingStationDto chargingStationDto;
//    private UserDto userDto;
}
