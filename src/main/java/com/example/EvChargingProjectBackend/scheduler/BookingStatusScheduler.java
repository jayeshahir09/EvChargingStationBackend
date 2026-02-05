package com.example.EvChargingProjectBackend.scheduler;

import com.example.EvChargingProjectBackend.entity.Booking;
import com.example.EvChargingProjectBackend.entity.type.BookingStatus;
import com.example.EvChargingProjectBackend.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class BookingStatusScheduler {
    private final BookingRepository bookingRepository;

    @Scheduled(fixedRate = 60000)
    public void updateBookingStatus(){
        LocalDateTime now = LocalDateTime.now();
        List<Booking> progressList = bookingRepository.findBookingsToStart(now);
        for(Booking b:progressList){
            b.setBookingStatus(BookingStatus.IN_PROGRESS);
        }
        bookingRepository.saveAll(progressList);

        List<Booking> completeList = bookingRepository.findBookingsToComplete(now);
        for(Booking b:completeList){
            b.setBookingStatus(BookingStatus.COMPLETED);
        }
        bookingRepository.saveAll(completeList);
    }

}
