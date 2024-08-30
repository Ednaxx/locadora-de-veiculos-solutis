package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.squad9.vehiclerentalservice.model.AccessoryModel;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.CarModelModel;
import org.squad9.vehiclerentalservice.repository.CarRepository;
import org.squad9.vehiclerentalservice.service.interfaces.CarService;
import org.squad9.vehiclerentalservice.service.util.DriverValidations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;

    @Override
    public List<CarModel> findAll() {
        try {
            return carRepository.findAll();
        }
        catch (Exception e){
            System.out.println("Não foi possível encontrar registros de carros!");
        }
        return null;
    }

    @Override
    public CarModel findById(@PathVariable UUID id) {
        try {
            Optional<CarModel> carroOptional = carRepository.findById(id);
            if (carroOptional.isPresent()) {
                return carroOptional.get();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    public void saveNewDates(CarModel carro){
        try{
            carRepository.save(carro);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<CarModel> findAvailableOnDate(String startDate, String returnDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parsedStartDate = LocalDate.parse(startDate, dateFormatter);
        LocalDate parsedReturnDate = LocalDate.parse(returnDate, dateFormatter);
        // TODO: Implement this
    }

    @Override
    public List<CarModel> findByCategory(UUID categoria_id) {
        // TODO: Implement this
    }

    @Override
    public List<CarModel> findByCarModel(UUID model_id) {
        // TODO: Implement this
    }

    @Override
    public List<CarModel> findByAcessorio(UUID accessorio_id) {
        // TODO: Implement this
    }

    @Override
    public CarModel save(CarModel carro) {
        try {
            if(!DriverValidations.isPlacaMercosulValida(carro.getLicensePlate()) && !DriverValidations.isPlacaComumValida(carro.getLicensePlate()))
                throw new IllegalArgumentException("Placa do car inválida!");

            if(!DriverValidations.isChassiValido(carro.getChassi()))
                throw new IllegalArgumentException("Chassi do car inválido!");

            if (carRepository.existsByLicensePlate(carro.getLicensePlate()))
                throw new IllegalArgumentException("Placa do car já existente no sistema!");

            if (carRepository.existsByChassi(carro.getChassi()))
                throw new IllegalArgumentException("Número de chassi já existente no sistema!");

            return carRepository.save(carro);
        }
        catch (Exception e) {
            throw new RuntimeException( e.getMessage());
        }
    }

    @Override
    public void remove(UUID id){
        try {
            carRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover car: " + e.getMessage());
        }
    }

    @Override
    public CarModel update(UUID id, CarModel car) {
        return null;
    }
}
