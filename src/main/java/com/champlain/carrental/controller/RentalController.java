package com.champlain.carrental.controller;
import com.champlain.carrental.domain.*;import com.champlain.carrental.dto.*;import com.champlain.carrental.service.*;import org.springframework.web.bind.annotation.*;import org.springframework.http.*;import jakarta.validation.Valid;import java.net.URI;import java.util.*;
@RestController @RequestMapping("/api/rentals") public class RentalController {
private final RentalService service;public RentalController(RentalService service){this.service=service;}
@GetMapping public ResponseEntity<List<RentalResponseDTO>> all(@RequestParam(required=false) RentalStatus status){return ResponseEntity.ok(service.all(status));}
@GetMapping("/{id}") public ResponseEntity<RentalResponseDTO> one(@PathVariable Long id){return ResponseEntity.ok(service.one(id));}
@PostMapping public ResponseEntity<RentalResponseDTO> create(@Valid @RequestBody RentalRequestDTO d){var result=service.create(d);return ResponseEntity.created(URI.create("/api/rentals/"+result.id())).body(result);}
@PutMapping("/{id}") public ResponseEntity<RentalResponseDTO> update(@PathVariable Long id,@Valid @RequestBody RentalRequestDTO d){return ResponseEntity.ok(service.update(id,d));}
@PatchMapping("/{id}/return") public ResponseEntity<RentalResponseDTO> returned(@PathVariable Long id){return ResponseEntity.ok(service.returned(id));}
@DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){service.delete(id);return ResponseEntity.noContent().build();}
@GetMapping("/{id}/details") public ResponseEntity<RentalWithDetailsResponseDTO> details(@PathVariable Long id){return ResponseEntity.ok(service.details(id));}
}