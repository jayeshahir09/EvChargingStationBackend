package com.example.EvChargingProjectBackend.service;

import com.example.EvChargingProjectBackend.dto.StationOwnerDto;
import com.example.EvChargingProjectBackend.dto.request.CreateOwnerRequestDto;
import com.example.EvChargingProjectBackend.dto.request.LoginOwnerRequestDto;

public interface StationOwnerService {
    public StationOwnerDto registerOwner(CreateOwnerRequestDto createOwnerRequestDto);
    public StationOwnerDto loginOwner(LoginOwnerRequestDto loginOwnerRequestDto);
}
