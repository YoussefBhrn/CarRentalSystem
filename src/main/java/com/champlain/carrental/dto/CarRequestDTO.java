package com.champlain.carrental.dto;
import com.champlain.carrental.domain.*;import jakarta.validation.constraints.*;import java.math.BigDecimal;
public record CarRequestDTO(@NotBlank String make,@NotBlank String model,@NotNull @Min(1990) Integer year,@NotBlank String licensePlate,@NotNull CarCategory category,@NotNull @DecimalMin("0.01") BigDecimal dailyRate,@NotNull CarStatus status) {}