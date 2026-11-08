package com.champlain.carrental.service;
import com.champlain.carrental.domain.*;import com.champlain.carrental.dto.*;import com.champlain.carrental.repository.*;import com.champlain.carrental.exception.*;import com.champlain.carrental.mapper.ApiMapper;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import java.util.*;
@Service @Transactional public class CarService {
private final CarRepository cars;private final RentalRepository rentals;
public CarService(CarRepository cars,RentalRepository rentals){this.cars=cars;this.rentals=rentals;}
public Car getEntity(Long id){return cars.findById(id).orElseThrow(()->new NotFoundException("Car "+id+" not found"));}
@Transactional(readOnly=true) public List<CarResponseDTO> all(CarStatus status,CarCategory category){return cars.findAll().stream().filter(c->status==null||c.status==status).filter(c->category==null||c.category==category).map(ApiMapper::car).toList();}
@Transactional(readOnly=true) public CarResponseDTO one(Long id){return ApiMapper.car(getEntity(id));}
public CarResponseDTO create(CarRequestDTO d){check(d,null);Car c=new Car();ApiMapper.update(c,d);return ApiMapper.car(cars.save(c));}
public CarResponseDTO update(Long id,CarRequestDTO d){Car c=getEntity(id);check(d,id);if(rentals.existsByCarIdAndStatusAndIdNot(id,RentalStatus.ACTIVE,-1L)&&d.status()!=CarStatus.RENTED)throw new ConflictException("An active rental requires RENTED status");if(!rentals.existsByCarIdAndStatusAndIdNot(id,RentalStatus.ACTIVE,-1L)&&d.status()==CarStatus.RENTED)throw new ConflictException("Use rental creation to mark a car rented");ApiMapper.update(c,d);return ApiMapper.car(cars.save(c));}
private void check(CarRequestDTO d,Long id){if(cars.existsByLicensePlateAndIdNot(d.licensePlate(),id==null?-1L:id))throw new ConflictException("License plate already exists");if(d.status()==CarStatus.RENTED&&id==null)throw new ConflictException("New cars cannot be RENTED without a rental");}
public void delete(Long id){Car c=getEntity(id);if(rentals.existsByCarId(id))throw new ConflictException("Car has rental history");cars.delete(c);}
@Transactional(readOnly=true) public CarWithRentalsResponseDTO withRentals(Long id){Car c=getEntity(id);return new CarWithRentalsResponseDTO(ApiMapper.car(c),rentals.findByCarId(id).stream().map(ApiMapper::rental).toList());}
}