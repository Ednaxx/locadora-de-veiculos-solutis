package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.dto.request.CarRequestDTO;
import org.squad9.vehiclerentalservice.dto.request.DateIntervalRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.CarResponseDTO;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.util.Category;
import org.squad9.vehiclerentalservice.repository.CarRepository;
import org.squad9.vehiclerentalservice.service.interfaces.CarService;
import org.squad9.vehiclerentalservice.service.util.DriverValidations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CarResponseDTO> findAll() {
        List<CarModel> cars = carRepository.findAll();
        List<CarResponseDTO> response = new ArrayList<>();

        cars.forEach(car -> response.add(modelMapper.map(car, CarResponseDTO.class)));
        return response;
    }

    @Override
    public CarResponseDTO findById(UUID id) {
        CarModel car = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Carro não encontrado com o ID: " + id));

        return modelMapper.map(car, CarResponseDTO.class);
    }

    // TODO: Check this later
    public void saveNewDates(CarModel carro){
        try{
            carRepository.save(carro);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<CarResponseDTO> findAvailableOnDate(DateIntervalRequestDTO dates) {
        List<CarModel> cars = carRepository.findAvailableOnDate(dates.getStartDate(), dates.getReturnDate());
        List<CarResponseDTO> response = new ArrayList<>();

        cars.forEach(car -> response.add(modelMapper.map(car, CarResponseDTO.class)));
        return response;
    }

    @Override
    public List<CarResponseDTO> findByCarModel(UUID modelId) {
        List<CarModel> cars =  carRepository.findByCarModelId(modelId);
        List<CarResponseDTO> response = new ArrayList<>();

        cars.forEach(car -> response.add(modelMapper.map(car, CarResponseDTO.class)));
        return response;
    }


    @Override
    public List<CarResponseDTO> findByCategory(Category category) {
        List<CarModel> cars = carRepository.findByCategory(category);
        List<CarResponseDTO> response = new ArrayList<>();

        cars.forEach(car -> response.add(modelMapper.map(car, CarResponseDTO.class)));
        return response;
    }

    @Override
    public List<CarResponseDTO> findByAcessorio(UUID accessoryId) {
        List<CarModel> cars = carRepository.findByAccessoriesId(accessoryId);
        List<CarResponseDTO> response = new ArrayList<>();

        cars.forEach(car -> response.add(modelMapper.map(car, CarResponseDTO.class)));
        return response;
    }

    @Override
    public CarResponseDTO save(CarRequestDTO request) {
        CarModel carToSave = modelMapper.map(request, CarModel.class);

        if(!DriverValidations.isPlacaMercosulValida(carToSave.getLicensePlate()) && !DriverValidations.isPlacaComumValida(carToSave.getLicensePlate()))
            throw new IllegalArgumentException("Placa do car inválida: " + carToSave.getLicensePlate());

        if(!DriverValidations.isChassiValido(carToSave.getChassis()))
            throw new IllegalArgumentException("Chassi do car inválido: " + carToSave.getChassis());

        if (carRepository.existsByLicensePlate(carToSave.getLicensePlate()))
            throw new IllegalArgumentException("Placa do car já existente no sistema: " +carToSave.getLicensePlate());

        if (carRepository.existsByChassi(carToSave.getChassis()))
            throw new IllegalArgumentException("Número de chassi já existente no sistema: " + carToSave.getLicensePlate());

        CarModel savedCar = carRepository.save(carToSave);
        return modelMapper.map(savedCar, CarResponseDTO.class);
    }

    @Override
    public void remove(UUID id){
        if (!carRepository.existsById(id)) {
            throw new IllegalArgumentException("Carro não encontrado com o ID: " + id);
        }
        carRepository.deleteById(id);
    }

    @Override
    public CarResponseDTO update(UUID id, CarRequestDTO request) {
        carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Acessório não encontrado com o ID: " + id));

        CarModel carToUpdate = modelMapper.map(request, CarModel.class);

        if(!DriverValidations.isPlacaMercosulValida(carToUpdate.getLicensePlate()) && !DriverValidations.isPlacaComumValida(carToUpdate.getLicensePlate()))
            throw new IllegalArgumentException("Placa do car inválida: " + carToUpdate.getLicensePlate());

        if(!DriverValidations.isChassiValido(carToUpdate.getChassis()))
            throw new IllegalArgumentException("Chassi do car inválido: " + carToUpdate.getChassis());

        if (carRepository.existsByLicensePlate(carToUpdate.getLicensePlate()))
            throw new IllegalArgumentException("Placa do car já existente no sistema: " +carToUpdate.getLicensePlate());

        if (carRepository.existsByChassi(carToUpdate.getChassis()))
            throw new IllegalArgumentException("Número de chassi já existente no sistema: " + carToUpdate.getLicensePlate());


        carToUpdate.setId(id);
        CarModel updatedCar = carRepository.save(carToUpdate);

        return modelMapper.map(updatedCar, CarResponseDTO.class);
    }


}
