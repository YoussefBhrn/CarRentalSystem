package com.champlain.carrental.dto;
import jakarta.validation.constraints.*;
public record CustomerRequestDTO(@NotBlank String firstName,@NotBlank String lastName,@NotBlank @Email String email,String phone,@NotBlank String driverLicenseNumber) {}