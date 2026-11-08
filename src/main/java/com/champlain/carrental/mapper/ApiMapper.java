package com.champlain.carrental.mapper;
import com.champlain.carrental.domain.*;import com.champlain.carrental.dto.*;
public class ApiMapper {
private ApiMapper(){}
public static CarResponseDTO car(Car c){return new CarResponseDTO(c.id,c.make,c.model,c.year,c.licensePlate,c.category,c.dailyRate,c.status);}
public static CustomerResponseDTO customer(Customer c){return new CustomerResponseDTO(c.id,c.firstName,c.lastName,c.email,c.phone,c.driverLicenseNumber);}
public static RentalResponseDTO rental(Rental r){return new RentalResponseDTO(r.id,r.car.id,r.customer.id,r.startDate,r.endDate,r.returnDate,r.totalPrice,r.status);}
public static void update(Car c,CarRequestDTO d){c.make=d.make();c.model=d.model();c.year=d.year();c.licensePlate=d.licensePlate();c.category=d.category();c.dailyRate=d.dailyRate();c.status=d.status();}
public static void update(Customer c,CustomerRequestDTO d){c.firstName=d.firstName();c.lastName=d.lastName();c.email=d.email();c.phone=d.phone();c.driverLicenseNumber=d.driverLicenseNumber();}
}