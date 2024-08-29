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

@Service
@AllArgsConstructor
public class RentalServiceImpl implements RentalService {

    private RentalRepository rentalRepository;
    private CarServiceImpl carroService;

    public List<RentalModel> findAll() {
        return rentalRepository.findAll();
    }

    public RentalModel save(RentalModel aluguel) {
        try {
            CarModel carro = aluguel.getCar();
            carro.bloquearDatas(aluguel.getDeliveryDate(), aluguel.getReturnDate());

            carroService.saveNewDates(carro);
            return rentalRepository.save(aluguel);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean processPayment(@RequestParam String cardNumber, @RequestParam String expirationDate, @RequestParam String cvv) {
        return verifyPayment(cardNumber, expirationDate, cvv);
    }

    public boolean verifyPayment(String cardNumber, String expirationDate, String cvv){

        if (!cardNumber.matches("\\d{13,16}")){
            System.out.println("fui eu: Cartão");
            return false;
        }
        if (!cvv.matches("\\d{3}")){
            System.out.println("fui eu: cvv");
            return false;
        }
        if (!isExpirationDateValid(expirationDate)){
            System.out.println("fui eu: Data");
            return false;
        }

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

    public List<RentalModel> findAlugueisMotorista(DriverModel motorista) {
        try{
            return rentalRepository.findByDriver(motorista);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
