package com.example.EvChargingProjectBackend.service.impl;

import com.example.EvChargingProjectBackend.dto.NotificationDto;
import com.example.EvChargingProjectBackend.entity.Notification;
import com.example.EvChargingProjectBackend.repository.NotificationRepository;
import com.example.EvChargingProjectBackend.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImp implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;
    public void marksAsRead(Long notificationId){
        Notification notification = notificationRepository.findById(notificationId).orElseThrow(()->new IllegalArgumentException("notification is not valid/found"));
        notification.setIsRead(true);
        notificationRepository.save(notification);
    }

    public List<NotificationDto> getUserNotifications(Long userId){
        List<Notification> notifications = notificationRepository.findByUserUserIdOrderByCreatedAtDesc(userId);
        return notifications.stream().map((notification)->modelMapper.map(notification, NotificationDto.class)).toList();
    }
}
