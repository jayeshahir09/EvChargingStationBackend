package com.example.EvChargingProjectBackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginOwnerRequestDto {
    @NotBlank
    private String email;
    @Size(min=6,max=15,message = "password must be between 6 to 15 characters")
    private String password;
}
