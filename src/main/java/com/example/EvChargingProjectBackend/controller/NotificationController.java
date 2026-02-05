package com.example.EvChargingProjectBackend.controller;

import com.example.EvChargingProjectBackend.dto.NotificationDto;
import com.example.EvChargingProjectBackend.entity.Notification;

import com.example.EvChargingProjectBackend.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationDto>> getUserNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getUserNotifications(userId));
    }

    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<String> markAsRead(@PathVariable Long notificationId) {
        notificationService.marksAsRead(notificationId);
        return ResponseEntity.ok("notification was read");
    }
}
