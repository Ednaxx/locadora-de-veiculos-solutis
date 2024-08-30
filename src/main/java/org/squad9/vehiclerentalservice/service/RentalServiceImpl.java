package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.squad9.vehiclerentalservice.model.RentalModel;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.DriverModel;
import org.squad9.vehiclerentalservice.repository.RentalRepository;
import org.squad9.vehiclerentalservice.service.interfaces.RentalService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalServiceImpl implements RentalService {

    private RentalRepository rentalRepository;
    private CarServiceImpl carService;

    @Override
    public List<RentalModel> findAll() {
        return rentalRepository.findAll();
    }

    @Override
    public RentalModel findById(UUID id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluguel não encontrado com o ID: " + id));
    }

    @Override
    public RentalModel save(RentalModel rental) {
        try {
            rental.setOrderDate(LocalDate.now());

            CarModel car = rental.getCar();
            car.blockDates(rental.getDeliveryDate(), rental.getReturnDate());

            carService.saveNewDates(car);
            return rentalRepository.save(rental);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            rentalRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover aluguel: " + e.getMessage());
        }
    }

    @Override
    public RentalModel update(UUID id, RentalModel rental) {
        try {
            RentalModel existingRental = rentalRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Aluguel não encontrado com o ID: " + id));

            existingRental.setDeliveryDate(rental.getDeliveryDate());
            existingRental.setReturnDate(rental.getReturnDate());
            existingRental.setTotalValue(rental.getTotalValue());
            existingRental.setInsurancePolicy(rental.getInsurancePolicy());
            existingRental.setCar(rental.getCar());
            existingRental.setDriver(rental.getDriver());

            return rentalRepository.save(existingRental);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar aluguel: " + e.getMessage());
        }
    }

    public boolean verifyPayment(Map<String, String> payload){
        String cardNumber = payload.get("cardNumber");
        String expirationDate = payload.get("expirationDate");
        String cvv = payload.get("cvv");

        if (!cardNumber.matches("\\d{13,16}")) return false;
        if (!cvv.matches("\\d{3}")) return false;
        if (!isExpirationDateValid(expirationDate)) return false;

        return true;
    }

    private boolean isExpirationDateValid(String expirationDate){
        String[] parts = expirationDate.split("/");
        int month = Integer.parseInt(parts[0]);
        int year = Integer.parseInt(parts[1]);

        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear() % 100;

        return ((year > currentYear) || (year == currentYear && month >= currentMonth));
    }
}

