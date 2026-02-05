package com.example.EvChargingProjectBackend.entity;

import com.example.EvChargingProjectBackend.entity.type.ChargerType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Charger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chargerId;
    @Enumerated(EnumType.STRING)
    private ChargerType chargerType;
    @Column(nullable = false)
    private Double powerKw;
    @Column(nullable = false)
    private Double pricePerUnit;
    @ManyToOne
    @JoinColumn(name = "station_id")
    private ChargingStation chargingStation;
    @OneToMany(mappedBy = "charger",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<Slot> slotList;
}
