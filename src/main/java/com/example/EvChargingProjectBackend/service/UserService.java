package com.example.EvChargingProjectBackend.service;

import com.example.EvChargingProjectBackend.dto.BookingDto;
import com.example.EvChargingProjectBackend.dto.UserDto;
import com.example.EvChargingProjectBackend.dto.request.CreateUserRequestDto;
import com.example.EvChargingProjectBackend.dto.request.LoginUserRequestDto;
import org.springframework.data.domain.Page;

import java.util.Map;


public interface UserService {
    public UserDto registerUser(CreateUserRequestDto createUserRequestDto);
    public UserDto loginUser(LoginUserRequestDto loginUserRequestDto);
    public UserDto findUser(Long userId);
    public void deleteUser(Long UserId);
    public UserDto partialUpdateUser(Long userId, Map<String,Object> updates);
}
