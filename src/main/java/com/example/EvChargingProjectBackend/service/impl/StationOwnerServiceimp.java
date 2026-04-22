package com.example.EvChargingProjectBackend.service.impl;

import com.example.EvChargingProjectBackend.dto.StationOwnerDto;
import com.example.EvChargingProjectBackend.dto.UserDto;
import com.example.EvChargingProjectBackend.dto.request.CreateOwnerRequestDto;
import com.example.EvChargingProjectBackend.dto.request.CreateUserRequestDto;
import com.example.EvChargingProjectBackend.dto.request.LoginOwnerRequestDto;
import com.example.EvChargingProjectBackend.dto.request.LoginUserRequestDto;
import com.example.EvChargingProjectBackend.entity.StationOwner;
import com.example.EvChargingProjectBackend.entity.User;
import com.example.EvChargingProjectBackend.repository.StationOwnerRepository;
import com.example.EvChargingProjectBackend.service.StationOwnerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StationOwnerServiceimp implements StationOwnerService {
    private final StationOwnerRepository stationOwnerRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public StationOwnerDto registerOwner(CreateOwnerRequestDto createOwnerRequestDto){
        if(stationOwnerRepository.existsByEmail(createOwnerRequestDto.getEmail())){
            throw new IllegalArgumentException("Email already exist");
        }
        if(stationOwnerRepository.existsByMobileNumber(createOwnerRequestDto.getMobileNumber())){
            throw new IllegalArgumentException("Mobile Number already exist");
        }
        StationOwner stationOwner = modelMapper.map(createOwnerRequestDto,StationOwner.class);
        stationOwner.setPassword(passwordEncoder.encode(stationOwner.getPassword()));
        StationOwner newOwner = stationOwnerRepository.save(stationOwner);
        StationOwnerDto ownerDto = modelMapper.map(newOwner,StationOwnerDto.class);
        return ownerDto;
    }

    public StationOwnerDto loginOwner(LoginOwnerRequestDto loginOwnerRequestDto){

        StationOwner stationOwner= stationOwnerRepository.findByEmail(loginOwnerRequestDto.getEmail()).orElseThrow(()->new IllegalArgumentException("email is not registered"));

        if(!passwordEncoder.matches(loginOwnerRequestDto.getPassword(), stationOwner.getPassword())){
            throw new IllegalArgumentException("password did not match");
        }

        return modelMapper.map(stationOwner,StationOwnerDto.class);
    }
}
