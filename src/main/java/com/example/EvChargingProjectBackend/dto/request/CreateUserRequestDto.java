package com.example.EvChargingProjectBackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequestDto {
    @Size(min=3,max=50,message = "name must be between 3 to 50 characters")
    private String name;
    @NotBlank
    private String email;
    @Size(max=10,min=10)
    private String mobileNumber;
    @Size(min=6,max=15,message = "password must be between 6 to 15 character")
    private String password;

}
