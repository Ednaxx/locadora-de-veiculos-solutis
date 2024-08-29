package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.model.RentalModel;
import org.squad9.vehiclerentalservice.model.DriverModel;
import org.squad9.vehiclerentalservice.service.RentalServiceImpl;
import org.squad9.vehiclerentalservice.service.DriverServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/alugueis")
@RequiredArgsConstructor
public class RentalController {

    private RentalServiceImpl rentalService;
    private DriverServiceImpl driverService;

    @GetMapping(value = "/{email}")
    public ResponseEntity<List<RentalModel>> findRentalsByDriver(@PathVariable String email){
        DriverModel motorista = driverService.findByEmail(email);
        List<RentalModel> rental = rentalService.findAlugueisMotorista(motorista);

        return ResponseEntity.ok(rental);
    }

    @GetMapping
    public ResponseEntity<List<RentalModel>> findAll() {
        List<RentalModel> rentals = rentalService.findAll();
        return ResponseEntity.ok(rentals);
    }

    @PostMapping("/pagamento-cartao")
    public ResponseEntity<String> processPayment(@RequestBody Map<String, String> payload) {
        String cardNumber = (payload.get("cardNumber"));
        String expirationDate = payload.get("expirationDate");
        String cvv = payload.get("cvv");

        boolean paymentSuccessful = rentalService.verifyPayment(cardNumber, expirationDate, cvv);

        return ResponseEntity.ok(paymentSuccessful ? "redirect:/resumo-reserva" : "redirect:/pagamento-falhou");
    }

    @PostMapping
    public ResponseEntity<RentalModel> create(@RequestBody RentalModel rental) {
        LocalDate orderDate = LocalDate.now();
        rental.setOrderDate(orderDate);
        System.out.println(rental.getOrderDate());

        RentalModel newRental = rentalService.save(rental);
        return ResponseEntity.ok(newRental);
    }
}
