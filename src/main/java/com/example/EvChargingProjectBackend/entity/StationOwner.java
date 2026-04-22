package com.example.EvChargingProjectBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StationOwner implements AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationOwnerId;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false,length=10)
    private String mobileNumber;
    @Column(nullable = false,length = 50)
    private String name;
    @Column(nullable = false)
    private String password;

    @Override
    public String getRole(){
        return "OWNER";
    }
    @OneToMany(mappedBy = "stationOwner", orphanRemoval = true,cascade = CascadeType.ALL)
    private List<ChargingStation> chargingStationList;
}
