package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.dto.request.PaymentRequestDTO;
import org.squad9.vehiclerentalservice.dto.request.RentalRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.RentalResponseDTO;
import org.squad9.vehiclerentalservice.model.RentalModel;
import org.squad9.vehiclerentalservice.service.RentalServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/alugueis")
@RequiredArgsConstructor
public class RentalController {
    private final RentalServiceImpl rentalService;

    @GetMapping
    ResponseEntity<List<RentalResponseDTO>> findAll() {
        List<RentalResponseDTO> response = rentalService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    ResponseEntity<RentalResponseDTO> findById(@PathVariable UUID id) {
        RentalResponseDTO response = rentalService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/motorista/{email}")
    ResponseEntity<List<RentalResponseDTO>> findRentalsByDriver(@PathVariable String email) {
        List<RentalResponseDTO> response = rentalService.findByDriverEmail(email);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    ResponseEntity<RentalResponseDTO> create(@RequestBody RentalRequestDTO request) {
        RentalResponseDTO response = rentalService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/pagamento-cartao")
    ResponseEntity<String> processPayment(@RequestBody PaymentRequestDTO request) {
        return ResponseEntity.ok("redirect:/resumo-reserva");
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id){
        rentalService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<RentalResponseDTO> update(@PathVariable UUID id, @RequestBody RentalRequestDTO request) {
        RentalResponseDTO response = rentalService.update(id, request);
        return ResponseEntity.ok(response);
    }
}
