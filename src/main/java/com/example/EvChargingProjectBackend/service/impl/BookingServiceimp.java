package com.example.EvChargingProjectBackend.service.impl;

import com.example.EvChargingProjectBackend.dto.BookingDto;
import com.example.EvChargingProjectBackend.dto.ChargerDto;
import com.example.EvChargingProjectBackend.dto.ChargingStationDto;
import com.example.EvChargingProjectBackend.dto.SlotDto;
import com.example.EvChargingProjectBackend.dto.request.CreateBookingRequest;
import com.example.EvChargingProjectBackend.entity.*;
import com.example.EvChargingProjectBackend.entity.type.BookingStatus;
import com.example.EvChargingProjectBackend.entity.type.SlotStatus;
import com.example.EvChargingProjectBackend.repository.BookingRepository;
import com.example.EvChargingProjectBackend.repository.SlotRepository;
import com.example.EvChargingProjectBackend.repository.UserRepository;
import com.example.EvChargingProjectBackend.service.BookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceimp implements BookingService {
    private final BookingRepository bookingRepository;
    private final SlotRepository slotRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public Page<BookingDto> getBookings(int page, int size){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Pageable pageable = PageRequest.of(page,size);
        Page<Booking> bookings = bookingRepository.findAllBookingByEmail(email,pageable);
        return bookings.map((booking)->modelMapper.map(booking,BookingDto.class));
    }

    @Transactional
    public BookingDto doBook(CreateBookingRequest createBookingRequest){
        SlotDto slotDto = createBookingRequest.getSlotDto();
        Long slotId = slotDto.getSlotId();
        Slot slot=slotRepository.findById(slotId).orElseThrow(()->new IllegalArgumentException("slot is not valid"));
        
//        Slot slot = slotRepository.findById(slotId).orElseThrow(()->new IllegalArgumentException("slot not found with this id"+slotId));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("user not found"));
        if(slot.getSlotStatus()==SlotStatus.BOOKED){
            throw new IllegalArgumentException("this slot is already Booked");
        }
        ChargerDto chargerDto = createBookingRequest.getChargerDto();
        Charger charger = modelMapper.map(chargerDto,Charger.class);
        ChargingStationDto stationDto = createBookingRequest.getChargingStationDto();
        ChargingStation station = modelMapper.map(stationDto,ChargingStation.class);

//        Charger charger = slot.getCharger();
//        LocalTime start = slot.getStartTime().toLocalTime();
//        LocalTime end = slot.getEndTime().toLocalTime();
//        double hours = Duration.between(start,end).toMinutes()/60.0;
//
//        double energy = hours* charger.getPowerKw();
//
//        double amount = energy*charger.getPricePerUnit();
        Booking booking = Booking.builder()
                .cost(createBookingRequest.getCost())
                .user(user)
                .slot(slot)
                .bookingStatus(BookingStatus.CONFIRMED)
                .build();
        slot.setSlotStatus(SlotStatus.BOOKED);
        booking.setCharger(charger);
        booking.setChargingStation(station);
        Booking newBooking = bookingRepository.save(booking);
        SlotDto slotDto1 = modelMapper.map(slot,SlotDto.class);
        BookingDto bookingDto = modelMapper.map(newBooking,BookingDto.class);
        bookingDto.setSlotDto(slotDto1);
        return bookingDto;
    }

    @Transactional
    public void deleteBooking(Long bookingId){
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()->new IllegalArgumentException("booking not found "));
//        User user = booking.getUser();
//        user.getBookingList().remove(booking);
//        userRepository.save(user);
        Slot slot = booking.getSlot();
        slot.setSlotStatus(SlotStatus.AVAILABLE);
        slot.setBooking(null);

        slotRepository.save(slot);
        bookingRepository.delete(booking);
    }

    public List<BookingDto> getAllBookings(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        List<Booking> bookings = bookingRepository.findAllBookingByEmail(email).orElseThrow(()-> new IllegalArgumentException("no Bookings wiht this user"));
        List<BookingDto> bookingDtos = new ArrayList<>();
        for(Booking booking:bookings){
            Charger charger=booking.getCharger();
            ChargingStation station = booking.getChargingStation();
            Slot slot = booking.getSlot();
            BookingDto bookingDto=modelMapper.map(booking,BookingDto.class);
            ChargerDto chargerDto = modelMapper.map(charger,ChargerDto.class);
            SlotDto slotDto = modelMapper.map(slot,SlotDto.class);
            ChargingStationDto stationDto = modelMapper.map(station,ChargingStationDto.class);
            bookingDto.setChargerDto(chargerDto);
            bookingDto.setSlotDto(slotDto);
            bookingDto.setChargingStationDto(stationDto);
            bookingDtos.add(bookingDto);
        }
        return bookingDtos;
    }

    public BookingDto cancelBooking(Long bookingId){
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()-> new IllegalArgumentException("booking not found"));
        booking.setBookingStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
        return modelMapper.map(booking,BookingDto.class);
    }
}
