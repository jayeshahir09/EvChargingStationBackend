package com.example.EvChargingProjectBackend.repository;

import com.example.EvChargingProjectBackend.entity.Booking;
import com.example.EvChargingProjectBackend.entity.Slot;
import com.example.EvChargingProjectBackend.entity.type.BookingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    @Query("""
            SELECT b FROM Booking b 
            WHERE b.user.email=:email
            ORDER BY b.slot.startTime DESC
            """)
    Page<Booking> findAllBookingByEmail(String email, Pageable pageable);

    @Query("SELECT b FROM Booking b WHERE b.user.userId =:userId " +
            "ORDER BY b.slot.startTime DESC")
    List<Booking> findAllBookings(@Param("userId")Long UserId);

    @Query("""
     SELECT b from Booking b WHERE
     b.bookingStatus = 'CONFIRMED'
     AND b.slot.startTime>=:now
     AND b.slot.endTime>:now
     """)
    List<Booking> findBookingsToStart(@Param("now")LocalDateTime now);

    @Query("""
    SELECT b FROM Booking b 
    WHERE b.bookingStatus='IN_PROGRESS'
    AND b.slot.endTime<=:now
    """)
    List<Booking> findBookingsToComplete(@Param("now")LocalDateTime now);

    @Query("""
            SELECT b FROM Booking b 
            WHERE b.user.email=:email
            ORDER BY b.slot.startTime DESC
            """)
    Optional<List<Booking>> findAllBookingByEmail(String email);

    @Query("""
SELECT b FROM Booking b
WHERE b.bookingStatus = :status
AND b.slot.startTime BETWEEN :start AND :end
""")
    List<Booking> findBookingsStartingBetween(
            LocalDateTime start,
            LocalDateTime end,  
            BookingStatus status
    );

    Optional<Slot> findAllBookingByUserEmail(String email);

}
