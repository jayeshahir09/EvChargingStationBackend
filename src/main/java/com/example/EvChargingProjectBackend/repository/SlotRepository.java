package com.example.EvChargingProjectBackend.repository;

import com.example.EvChargingProjectBackend.entity.Slot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public interface SlotRepository extends JpaRepository<Slot,Long> {
    Page<Slot> findAllSlotsByChargerChargerId(Long chargerChargerId, Pageable pageable);

    @Query("SELECT s FROM Slot s WHERE s.charger.chargerId = :chargerId " +
            "AND FUNCTION('date',s.startTime)= :date " +
            "ORDER BY s.startTime" )
    List<Slot> findAllSlotsByChargerAndDate(Long chargerId, LocalDate date);

    @Query("SELECT p FROM Slot p WHERE p.charger.chargerId=:chargerId AND FUNCTION('date',p.startTime)=:date " +
            " AND FUNCTION('time',p.startTime)> :time")
    List<Slot> findAllSlotsByToday(Long chargerId, LocalDate date, LocalTime time);
}
