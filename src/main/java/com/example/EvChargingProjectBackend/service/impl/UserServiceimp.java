package com.example.EvChargingProjectBackend.service.impl;

import com.example.EvChargingProjectBackend.dto.BookingDto;
import com.example.EvChargingProjectBackend.dto.UserDto;
import com.example.EvChargingProjectBackend.dto.request.CreateUserRequestDto;
import com.example.EvChargingProjectBackend.dto.request.LoginUserRequestDto;
import com.example.EvChargingProjectBackend.entity.Booking;
import com.example.EvChargingProjectBackend.entity.User;
import com.example.EvChargingProjectBackend.repository.BookingRepository;
import com.example.EvChargingProjectBackend.repository.UserRepository;
import com.example.EvChargingProjectBackend.security.JWTService;
import com.example.EvChargingProjectBackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceimp implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final BookingRepository bookingRepository;
    public UserDto registerUser(CreateUserRequestDto createUserRequestDto){
        if(userRepository.existsByEmail(createUserRequestDto.getEmail())){
            throw new IllegalArgumentException("Email already exist");
        }
        if(userRepository.existsByMobileNumber(createUserRequestDto.getMobileNumber())){
            throw new IllegalArgumentException("Mobile Number already exist");
        }
        User user = modelMapper.map(createUserRequestDto,User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userRepository.save(user);
        UserDto userDto = modelMapper.map(newUser,UserDto.class);
        return userDto;
    }

    public String loginUser(LoginUserRequestDto user){
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));

        if(authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return jwtService.generateToken(userDetails);
        }
        return "fail";
    }



    public void deleteUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("user not found"));
        userRepository.delete(user);
    }

    public UserDto findUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("user not found "));
        return modelMapper.map(user,UserDto.class);
    }

    public UserDto partialUpdateUser( Map<String,Object> updates){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findByEmail(email).orElseThrow(()-> new IllegalArgumentException("user not found"));
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
