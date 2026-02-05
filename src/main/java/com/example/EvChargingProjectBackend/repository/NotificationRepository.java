package com.example.EvChargingProjectBackend.repository;

import com.example.EvChargingProjectBackend.entity.Booking;
import com.example.EvChargingProjectBackend.entity.Notification;
import com.example.EvChargingProjectBackend.entity.type.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long>  {

    List<Notification> findByUserUserIdOrderByCreatedAtDesc(Long userId);
}
