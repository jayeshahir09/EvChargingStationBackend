package com.example.EvChargingProjectBackend.service;

import com.example.EvChargingProjectBackend.dto.NotificationDto;
import com.example.EvChargingProjectBackend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface NotificationService {
    public List<NotificationDto> getUserNotifications(Long userId);
    public void marksAsRead(Long notificationId);
}
