package com.example.EvChargingProjectBackend.repository;

import com.example.EvChargingProjectBackend.entity.StationOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StationOwnerRepository extends JpaRepository<StationOwner, Long> {
    boolean existsByMobileNumber(String  mobileNumber);
    boolean existsByEmail(String email);
    Optional<StationOwner> findByEmail(String email);
}
