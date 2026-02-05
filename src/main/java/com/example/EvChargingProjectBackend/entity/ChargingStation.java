package com.example.EvChargingProjectBackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ChargingStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationId;
    @Column(nullable = false,length=100)
    private String stationName;
    @Column(nullable = false,length=100)
    private String address;
    @Column(nullable = false,length=30)
    private String city;
    @Column(nullable = false,length=20)
    private String state;

    private Double latitude;
    private Double longitude;

    @ManyToOne
    private StationOwner stationOwner;

    @OneToMany(mappedBy = "chargingStation", orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Charger> chargerList;
}
