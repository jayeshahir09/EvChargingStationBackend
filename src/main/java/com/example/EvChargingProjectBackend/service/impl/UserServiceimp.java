package com.example.EvChargingProjectBackend.service.impl;

import com.example.EvChargingProjectBackend.dto.BookingDto;
import com.example.EvChargingProjectBackend.dto.UserDto;
import com.example.EvChargingProjectBackend.dto.request.CreateUserRequestDto;
import com.example.EvChargingProjectBackend.dto.request.LoginUserRequestDto;
import com.example.EvChargingProjectBackend.entity.Booking;
import com.example.EvChargingProjectBackend.entity.User;
import com.example.EvChargingProjectBackend.repository.BookingRepository;
import com.example.EvChargingProjectBackend.repository.UserRepository;
import com.example.EvChargingProjectBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceimp implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BookingRepository bookingRepository;
    public UserDto registerUser(CreateUserRequestDto createUserRequestDto){
        if(userRepository.existsByEmail(createUserRequestDto.getEmail())){
            throw new IllegalArgumentException("Email already exist");
        }
        if(userRepository.existsByMobileNumber(createUserRequestDto.getMobileNumber())){
            throw new IllegalArgumentException("Mobile Number already exist");
        }
        User user = modelMapper.map(createUserRequestDto,User.class);
        User newUser = userRepository.save(user);
        UserDto userDto = modelMapper.map(newUser,UserDto.class);
        return userDto;
    }

    public UserDto loginUser(LoginUserRequestDto loginUserRequestDto){
        User user = userRepository.findByEmail(loginUserRequestDto.getEmail()).orElseThrow(()->new IllegalArgumentException("email is not registered"));
        if(!user.getPassword().equals(loginUserRequestDto.getPassword())){
            throw new IllegalArgumentException("password did not match");
        }
        UserDto userDto = modelMapper.map(user,UserDto.class);
        return userDto;
    }



    public void deleteUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("user not found with this id "+userId));
        userRepository.delete(user);
    }

    public UserDto findUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("user not found "));
        return modelMapper.map(user,UserDto.class);
    }

    public UserDto partialUpdateUser(Long userId, Map<String,Object> updates){
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("user not found"));
        updates.forEach((fields,value)->{
            switch(fields){
                case "name":
                    user.setName(value.toString());
                    break;
                case "email":
                    user.setEmail(value.toString());
                    break;
                case "mobileNumber":
                    user.setMobileNumber(value.toString());
                    break;
                default:
                    new IllegalArgumentException("in valid fields");
            }
        });
        userRepository.save(user);
        return modelMapper.map(user,UserDto.class);
    }
}
