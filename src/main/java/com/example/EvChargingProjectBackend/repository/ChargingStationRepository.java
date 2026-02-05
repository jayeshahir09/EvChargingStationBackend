package com.example.EvChargingProjectBackend.repository;

import com.example.EvChargingProjectBackend.dto.NearbyStation;
import com.example.EvChargingProjectBackend.entity.ChargingStation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChargingStationRepository extends JpaRepository<ChargingStation,Long> {
    @Query("SELECT s FROM ChargingStation s WHERE s.stationOwner.stationOwnerId = :ownerId")
    Page<ChargingStation> findAllStationByOwnerId(@Param("ownerId") Long ownerId, Pageable pageable);

    @Query("""
SELECT s FROM ChargingStation s WHERE LOWER(s.stationName) LIKE LOWER(CONCAT('%',:words,'%'))
OR LOWER(s.address) LIKE LOWER(CONCAT('%',:words,'%')) OR LOWER(s.city) LIKE LOWER(CONCAT('%',:words,'%'))
OR LOWER(s.state) LIKE LOWER(CONCAT('%',:words,'%'))
""")
    Page<ChargingStation> searchChargingStation(String words, Pageable pageable);

    @Query("""
SELECT new com.example.EvChargingProjectBackend.dto.NearbyStation(
    s,
    (6371 * acos(
        cos(radians(:lat)) * cos(radians(s.latitude)) *
        cos(radians(s.longitude) - radians(:lng)) +
        sin(radians(:lat)) * sin(radians(s.latitude))
    ))
)
FROM ChargingStation s
ORDER BY
(6371 * acos(
    cos(radians(:lat)) * cos(radians(s.latitude)) *
    cos(radians(s.longitude) - radians(:lng)) +
    sin(radians(:lat)) * sin(radians(s.latitude))
))
""")
    Page<NearbyStation> findNearChargingStation(@Param("lat") Double lat, @Param("lng") Double lng, Pageable pageable);

}
