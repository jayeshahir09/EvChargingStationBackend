package com.example.EvChargingProjectBackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(length=50,nullable = false)
    private String name;
    @Column(length=10,nullable = false,unique = true)
    private String mobileNumber;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false,length=15)
    private String password;

    private Double latitude;
    private Double longitude;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Booking> bookingList;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Notification> notificationList;
}

