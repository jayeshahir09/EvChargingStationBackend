package com.example.EvChargingProjectBackend.entity;

import com.example.EvChargingProjectBackend.entity.type.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private Double cost;
    @Enumerated(value = EnumType.STRING)
    private BookingStatus bookingStatus;
    @OneToOne
    private Slot slot;
    @ManyToOne
    private Charger charger;
    @ManyToOne
    private ChargingStation chargingStation;
    @ManyToOne
    private User user;
}
