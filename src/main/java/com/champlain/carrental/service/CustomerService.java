package com.champlain.carrental.service;
import com.champlain.carrental.domain.*;import com.champlain.carrental.dto.*;import com.champlain.carrental.repository.*;import com.champlain.carrental.exception.*;import com.champlain.carrental.mapper.ApiMapper;import org.springframework.stereotype.Service;import org.springframework.transaction.annotation.Transactional;import java.util.*;
@Service @Transactional public class CustomerService {
private final CustomerRepository customers;private final RentalRepository rentals;
public CustomerService(CustomerRepository customers,RentalRepository rentals){this.customers=customers;this.rentals=rentals;}
public Customer getEntity(Long id){return customers.findById(id).orElseThrow(()->new NotFoundException("Customer "+id+" not found"));}
@Transactional(readOnly=true) public List<CustomerResponseDTO> all(String lastName){return (lastName==null?customers.findAll():customers.findByLastNameContainingIgnoreCase(lastName)).stream().map(ApiMapper::customer).toList();}
@Transactional(readOnly=true) public CustomerResponseDTO one(Long id){return ApiMapper.customer(getEntity(id));}
public CustomerResponseDTO create(CustomerRequestDTO d){check(d,null);Customer c=new Customer();ApiMapper.update(c,d);return ApiMapper.customer(customers.save(c));}
public CustomerResponseDTO update(Long id,CustomerRequestDTO d){Customer c=getEntity(id);check(d,id);ApiMapper.update(c,d);return ApiMapper.customer(customers.save(c));}
private void check(CustomerRequestDTO d,Long id){long exclude=id==null?-1L:id;if(customers.existsByEmailAndIdNot(d.email(),exclude))throw new ConflictException("Email already exists");if(customers.existsByDriverLicenseNumberAndIdNot(d.driverLicenseNumber(),exclude))throw new ConflictException("Driver license already exists");}
public void delete(Long id){Customer c=getEntity(id);if(rentals.existsByCustomerId(id))throw new ConflictException("Customer has rental history");customers.delete(c);}
@Transactional(readOnly=true) public List<RentalResponseDTO> rentals(Long id){getEntity(id);return rentals.findByCustomerId(id).stream().map(ApiMapper::rental).toList();}
@Transactional(readOnly=true) public List<CarResponseDTO> cars(Long id){getEntity(id);return rentals.findByCustomerId(id).stream().map(r->ApiMapper.car(r.car)).distinct().toList();}
}