package com.example.EvChargingProjectBackend.controller;

import com.example.EvChargingProjectBackend.dto.BookingDto;
import com.example.EvChargingProjectBackend.dto.request.CreateBookingRequest;
import com.example.EvChargingProjectBackend.service.BookingService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
@CrossOrigin(origins="http://localhost:5173")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/{userId}")
    public ResponseEntity<BookingDto> doBook(@RequestBody CreateBookingRequest createBookingRequest, @PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.doBook(createBookingRequest,userId));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long bookingId){
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.ok("booking canceled successfully");
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<BookingDto> cancelBooking(@PathVariable Long bookingId){
        return ResponseEntity.ok(bookingService.cancelBooking(bookingId));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<Page<BookingDto>> getBookings(@PathVariable Long userId, @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "6") int size){
        return ResponseEntity.ok(bookingService.getBookings(userId,page,size));
    }

    @GetMapping("/{userId}/allBooking")
    public ResponseEntity<List<BookingDto>> getAllBooking(@PathVariable Long userId){
        return ResponseEntity.ok(bookingService.getAllBookings(userId));
    }
}
