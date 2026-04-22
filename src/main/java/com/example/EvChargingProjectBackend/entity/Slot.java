package com.example.EvChargingProjectBackend.entity;

import com.example.EvChargingProjectBackend.entity.type.SlotStatus;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.connector.Connector;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter@Builder

public class Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long slotId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Enumerated(EnumType.STRING)
    private SlotStatus slotStatus = SlotStatus.AVAILABLE;

    @ManyToOne
    private Charger charger;
    @OneToOne(mappedBy = "slot",orphanRemoval = true,cascade = CascadeType.ALL)
    private Booking booking;
}
