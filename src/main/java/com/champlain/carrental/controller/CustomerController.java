package com.champlain.carrental.controller;
import com.champlain.carrental.dto.*;import com.champlain.carrental.service.*;import org.springframework.web.bind.annotation.*;import org.springframework.http.*;import jakarta.validation.Valid;import java.net.URI;import java.util.*;
@RestController @RequestMapping("/api/customers") public class CustomerController {
private final CustomerService service;public CustomerController(CustomerService service){this.service=service;}
@GetMapping public ResponseEntity<List<CustomerResponseDTO>> all(@RequestParam(required=false) String lastName){return ResponseEntity.ok(service.all(lastName));}
@GetMapping("/{id}") public ResponseEntity<CustomerResponseDTO> one(@PathVariable Long id){return ResponseEntity.ok(service.one(id));}
@PostMapping public ResponseEntity<CustomerResponseDTO> create(@Valid @RequestBody CustomerRequestDTO d){var result=service.create(d);return ResponseEntity.created(URI.create("/api/customers/"+result.id())).body(result);}
@PutMapping("/{id}") public ResponseEntity<CustomerResponseDTO> update(@PathVariable Long id,@Valid @RequestBody CustomerRequestDTO d){return ResponseEntity.ok(service.update(id,d));}
@DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){service.delete(id);return ResponseEntity.noContent().build();}
@GetMapping("/{id}/rentals") public ResponseEntity<List<RentalResponseDTO>> rentals(@PathVariable Long id){return ResponseEntity.ok(service.rentals(id));}
@GetMapping("/{id}/cars") public ResponseEntity<List<CarResponseDTO>> cars(@PathVariable Long id){return ResponseEntity.ok(service.cars(id));}
}