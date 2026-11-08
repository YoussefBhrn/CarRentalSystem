package com.champlain.carrental.dto;
import com.champlain.carrental.domain.RentalStatus;import java.math.BigDecimal;import java.time.LocalDate;
public record RentalResponseDTO(Long id,Long carId,Long customerId,LocalDate startDate,LocalDate endDate,LocalDate returnDate,BigDecimal totalPrice,RentalStatus status) {}