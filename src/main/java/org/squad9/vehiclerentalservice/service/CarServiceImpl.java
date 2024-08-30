package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.util.Category;
import org.squad9.vehiclerentalservice.repository.CarRepository;
import org.squad9.vehiclerentalservice.service.interfaces.CarService;
import org.squad9.vehiclerentalservice.service.util.DriverValidations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public List<CarModel> findAll() {
        try {
            return carRepository.findAll();
        }
        catch (Exception e){
            throw new RuntimeException("Não foi possível encontrar registros de carros!");
        }
    }

    @Override
    public CarModel findById(UUID id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
    }

    public void saveNewDates(CarModel carro){
        try{
            carRepository.save(carro);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<CarModel> findAvailableOnDate(String startDate, String returnDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parsedStartDate = LocalDate.parse(startDate, dateFormatter);
        LocalDate parsedReturnDate = LocalDate.parse(returnDate, dateFormatter);

        return carRepository.findAvailableOnDate(parsedStartDate, parsedReturnDate);
    }

    @Override
    public List<CarModel> findByCarModel(UUID modelId) {
        return carRepository.findByCarModelId(modelId);
    }


    @Override
    public List<CarModel> findByCategory(Category category) {
        return carRepository.findByCategory(category);
    }

    @Override
    public List<CarModel> findByAcessorio(UUID accessoryId) {
        return carRepository.findByAccessoriesId(accessoryId);
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
    public CarModel update(UUID id, CarModel updatedCar) {
        CarModel existingCar = findById(id);

        existingCar.setLicensePlate(updatedCar.getLicensePlate());
        existingCar.setChassi(updatedCar.getChassi());
        existingCar.setColor(updatedCar.getColor());
        existingCar.setDailyRate(updatedCar.getDailyRate());
        existingCar.setImageURL(updatedCar.getImageURL());
        existingCar.setCarModel(updatedCar.getCarModel());
        existingCar.setAccessories(updatedCar.getAccessories());
        existingCar.setOccupiedDates(updatedCar.getOccupiedDates());

        return carRepository.save(existingCar);
    }


}
