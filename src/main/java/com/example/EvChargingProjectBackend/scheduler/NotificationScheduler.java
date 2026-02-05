package com.example.EvChargingProjectBackend.scheduler;

import com.example.EvChargingProjectBackend.entity.Booking;
import com.example.EvChargingProjectBackend.entity.Notification;
import com.example.EvChargingProjectBackend.entity.type.BookingStatus;
import com.example.EvChargingProjectBackend.repository.BookingRepository;
import com.example.EvChargingProjectBackend.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationScheduler {
    private final NotificationRepository notificationRepository;
    private final BookingRepository bookingRepository;

    @Scheduled(fixedRate=60000)
    public void sendSlotStartNotifications(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime notifyTime = now.plusMinutes(10);
        List<Booking> bookings = bookingRepository.findBookingsStartingBetween(now,notifyTime, BookingStatus.CONFIRMED);

        for(Booking booking:bookings){
            Notification notification = new Notification();
            notification.setUser(booking.getUser());
            notification.setTitle("charging slot Reminder ");
            notification.setMessage("Your charging slot starts at"+booking.getSlot().getStartTime());
            notification.setIsRead(false);
            notification.setCreatedAt(LocalDateTime.now());
            notificationRepository.save(notification);
        }
    }
}
