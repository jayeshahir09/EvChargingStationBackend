package com.example.EvChargingProjectBackend.service;

import com.example.EvChargingProjectBackend.dto.BookingDto;
import com.example.EvChargingProjectBackend.dto.UserDto;
import com.example.EvChargingProjectBackend.dto.request.CreateUserRequestDto;
import com.example.EvChargingProjectBackend.dto.request.LoginUserRequestDto;
import org.springframework.data.domain.Page;

import java.util.Map;


public interface UserService {
    public UserDto registerUser(CreateUserRequestDto createUserRequestDto);
    public String loginUser(LoginUserRequestDto loginUserRequestDto);
    public UserDto findUser();
    public void deleteUser();
    public UserDto partialUpdateUser( Map<String,Object> updates);
}
