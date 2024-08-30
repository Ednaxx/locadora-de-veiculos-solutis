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

@Service
@AllArgsConstructor
public class RentalServiceImpl implements RentalService {

    private RentalRepository rentalRepository;
    private CarServiceImpl carroService;

    public List<RentalModel> findAll() {
        return rentalRepository.findAll();
    }

    public RentalModel save(RentalModel rental) {
        try {
            rental.setOrderDate(LocalDate.now());

            CarModel carro = rental.getCar();
            carro.blockDates(rental.getDeliveryDate(), rental.getReturnDate());

            carroService.saveNewDates(carro);
            return rentalRepository.save(rental);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
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

        System.out.println(month + " / " + currentMonth);
        System.out.println(year + " / " + currentYear);

        return ((year > currentYear) || (year == currentYear && month >= currentMonth));
    }
}
