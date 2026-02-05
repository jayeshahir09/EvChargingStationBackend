package com.example.EvChargingProjectBackend.controller;

import com.example.EvChargingProjectBackend.dto.*;
import com.example.EvChargingProjectBackend.dto.request.CreateUserRequestDto;
import com.example.EvChargingProjectBackend.dto.request.LoginUserRequestDto;
import com.example.EvChargingProjectBackend.service.ChargingStationService;
import com.example.EvChargingProjectBackend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins="http://localhost:5173")
public class UserController {
    private final UserService userService;
    private final ChargingStationService chargingStationService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid@RequestBody CreateUserRequestDto createUserRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(createUserRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@Valid @RequestBody LoginUserRequestDto loginUserRequestDto){
        return ResponseEntity.ok(userService.loginUser(loginUserRequestDto));
    }



    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("user deleted suceesfully");
    }



//    @GetMapping("/filter")
//    public ResponseEntity<Page<ChargingStationDto>> filterChargingStation()



    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findUser(@PathVariable Long userId){
        return ResponseEntity.ok(userService.findUser(userId));
    }

    @PatchMapping("/{userId}")  
    public ResponseEntity<UserDto> partialUpdateUser(@PathVariable Long userId, @RequestBody Map<String,Object> updates){
        return ResponseEntity.ok(userService.partialUpdateUser(userId,updates));
    }
}
