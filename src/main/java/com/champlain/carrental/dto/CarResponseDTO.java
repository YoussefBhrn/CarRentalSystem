package com.champlain.carrental.dto;
import com.champlain.carrental.domain.*;import java.math.BigDecimal;
public record CarResponseDTO(Long id,String make,String model,Integer year,String licensePlate,CarCategory category,BigDecimal dailyRate,CarStatus status) {}