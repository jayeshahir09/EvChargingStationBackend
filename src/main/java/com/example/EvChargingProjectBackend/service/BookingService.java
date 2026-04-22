package com.example.EvChargingProjectBackend.service;


import com.example.EvChargingProjectBackend.dto.BookingDto;
import com.example.EvChargingProjectBackend.dto.request.CreateBookingRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookingService {
    public BookingDto doBook(CreateBookingRequest createBookingRequest);
    public void deleteBooking(Long bookingId);
    public BookingDto cancelBooking(Long bookingId);
    public Page<BookingDto> getBookings( int page, int size);
    public List<BookingDto> getAllBookings();
}
