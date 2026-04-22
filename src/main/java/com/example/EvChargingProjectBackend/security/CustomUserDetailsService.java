package com.example.EvChargingProjectBackend.security;

import com.example.EvChargingProjectBackend.entity.AppUser;
import com.example.EvChargingProjectBackend.repository.StationOwnerRepository;
import com.example.EvChargingProjectBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private StationOwnerRepository ownerRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AppUser appUser = userRepo.findByEmail(email)
                .map(u -> (AppUser) u)
                .orElseGet(() -> ownerRepo.findByEmail(email)
                        .map(o -> (AppUser) o)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found")));

        return new org.springframework.security.core.userdetails.User(
                appUser.getEmail(),
                appUser.getPassword(),
                List.of(new SimpleGrantedAuthority(appUser.getRole()))
        );
    }
}
