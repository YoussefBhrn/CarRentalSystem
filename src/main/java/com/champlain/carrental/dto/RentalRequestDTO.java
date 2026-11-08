package com.champlain.carrental.dto;
import jakarta.validation.constraints.*;import java.time.LocalDate;
public record RentalRequestDTO(@NotNull Long carId,@NotNull Long customerId,@NotNull LocalDate startDate,@NotNull LocalDate endDate) {}