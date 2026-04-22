package com.example.EvChargingProjectBackend.entity;

import com.example.EvChargingProjectBackend.entity.type.ChargerType;
import com.example.EvChargingProjectBackend.entity.type.ConnectorType;
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
    @Enumerated(EnumType.STRING)
    private ConnectorType connectorType;
    @Column(nullable = false)
    private Double powerKw;
    @Column(nullable = false)
    private Double pricePerUnit;

    @OneToMany(mappedBy = "charger")
    private List<Slot> slotList;
    @ManyToOne
    @JoinColumn(name = "station_id")
    private ChargingStation chargingStation;
}
