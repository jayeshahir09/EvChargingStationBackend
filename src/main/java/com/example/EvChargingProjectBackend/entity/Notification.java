package com.example.EvChargingProjectBackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;
    @Column(length = 50,nullable = false)
    private String title;
    @Column(length = 100,nullable = false)
    private String message;
    private Boolean isRead;
    private LocalDateTime createdAt;

    @ManyToOne()
    private User user;
}
