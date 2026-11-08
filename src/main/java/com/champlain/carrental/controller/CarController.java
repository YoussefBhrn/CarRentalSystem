package com.champlain.carrental.controller;
import com.champlain.carrental.domain.*;import com.champlain.carrental.dto.*;import com.champlain.carrental.service.*;import org.springframework.web.bind.annotation.*;import org.springframework.http.*;import jakarta.validation.Valid;import java.net.URI;import java.util.*;
@RestController @RequestMapping("/api/cars") public class CarController {
private final CarService service;public CarController(CarService service){this.service=service;}
@GetMapping public ResponseEntity<List<CarResponseDTO>> all(@RequestParam(required=false) CarStatus status,@RequestParam(required=false) CarCategory category){return ResponseEntity.ok(service.all(status,category));}
@GetMapping("/{id}") public ResponseEntity<CarResponseDTO> one(@PathVariable Long id){return ResponseEntity.ok(service.one(id));}
@PostMapping public ResponseEntity<CarResponseDTO> create(@Valid @RequestBody CarRequestDTO d){var result=service.create(d);return ResponseEntity.created(URI.create("/api/cars/"+result.id())).body(result);}
@PutMapping("/{id}") public ResponseEntity<CarResponseDTO> update(@PathVariable Long id,@Valid @RequestBody CarRequestDTO d){return ResponseEntity.ok(service.update(id,d));}
@DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){service.delete(id);return ResponseEntity.noContent().build();}
@GetMapping("/{id}/rentals") public ResponseEntity<CarWithRentalsResponseDTO> rentals(@PathVariable Long id){return ResponseEntity.ok(service.withRentals(id));}
}