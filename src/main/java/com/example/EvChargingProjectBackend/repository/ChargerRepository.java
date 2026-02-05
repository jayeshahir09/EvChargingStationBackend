package com.example.EvChargingProjectBackend.repository;

import com.example.EvChargingProjectBackend.entity.Charger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChargerRepository extends JpaRepository<Charger,Long> {
    public List<Charger> findChargerByChargingStationStationId(Long chargingStationId);

    List<Charger> findAllByChargingStationStationId(Long chargingStationStationId);
}
