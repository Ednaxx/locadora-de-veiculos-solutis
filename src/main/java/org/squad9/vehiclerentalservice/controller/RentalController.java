package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    ResponseEntity<List<RentalModel>> findAll() {
        List<RentalModel> rentals = rentalService.findAll();
        return ResponseEntity.ok(rentals);
    }

    @GetMapping("/{id}")
    ResponseEntity<RentalModel> findById(@PathVariable UUID id) {
        RentalModel rentalModel = rentalService.findById(id);
        return ResponseEntity.ok(rentalModel);
    }

    @GetMapping("/motorista/{email}")
    ResponseEntity<List<RentalModel>> findRentalsByDriver(@PathVariable String email) {
        List<RentalModel> rentals = rentalService.findByDriverEmail(email);
        return ResponseEntity.ok(rentals);
    }


    @PostMapping
    ResponseEntity<RentalModel> create(@RequestBody RentalModel rental) {
        RentalModel newRental = rentalService.save(rental);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRental);
    }

    @PostMapping("/pagamento-cartao")
    ResponseEntity<String> processPayment(@RequestBody Map<String, String> payload) {
        boolean paymentSuccessful = rentalService.verifyPayment(payload);
        return paymentSuccessful ? ResponseEntity.ok("redirect:/resumo-reserva") : ResponseEntity.badRequest().body("redirect:/pagamento-falhou");
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id){
        rentalService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<RentalModel> update(@PathVariable UUID id, @RequestBody RentalModel rental) {
        RentalModel rentalModel = rentalService.update(id, rental);
        return ResponseEntity.ok(rentalModel);
    }
}
