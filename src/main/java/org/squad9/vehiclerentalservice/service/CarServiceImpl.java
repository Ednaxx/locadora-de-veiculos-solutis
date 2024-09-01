package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.dto.request.CarRequestDTO;
import org.squad9.vehiclerentalservice.dto.request.DateIntervalRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.AccessoryResponseDTO;
import org.squad9.vehiclerentalservice.dto.response.CarResponseDTO;
import org.squad9.vehiclerentalservice.enums.Category;
import org.squad9.vehiclerentalservice.model.AccessoryModel;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.repository.AccessoryRepository;
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
    private final AccessoryRepository accessoryRepository;
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
        CarModel car = carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Carro não encontrado com o ID: " + id));

        return modelMapper.map(car, CarResponseDTO.class);
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
        List<CarModel> cars = carRepository.findByCarModelId(modelId);
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

        if (!DriverValidations.isMercosurLicensePlateValid(carToSave.getLicensePlate()) && !DriverValidations.isStandardLicensePlateValid(carToSave.getLicensePlate()))
            throw new IllegalArgumentException("Placa do car inválida: " + carToSave.getLicensePlate());

        if (!DriverValidations.isChassisValid(carToSave.getChassis()))
            throw new IllegalArgumentException("Chassi do car inválido: " + carToSave.getChassis());

        if (carRepository.existsByLicensePlate(carToSave.getLicensePlate()))
            throw new IllegalArgumentException("Placa do car já existente no sistema: " + carToSave.getLicensePlate());

        if (carRepository.existsByChassis(carToSave.getChassis()))
            throw new IllegalArgumentException("Número de chassi já existente no sistema: " + carToSave.getLicensePlate());

        CarModel savedCar = carRepository.save(carToSave);
        return modelMapper.map(savedCar, CarResponseDTO.class);
    }

    @Override
    public void remove(UUID id) {
        if (!carRepository.existsById(id)) {
            throw new IllegalArgumentException("Carro não encontrado com o ID: " + id);
        }
        carRepository.deleteById(id);
    }

    @Override
    public CarResponseDTO update(UUID id, CarRequestDTO request) {
        CarModel oldCar = carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Acessório não encontrado com o ID: " + id));

        CarModel carToUpdate = modelMapper.map(request, CarModel.class);

        if (!DriverValidations.isMercosurLicensePlateValid(carToUpdate.getLicensePlate()) && !DriverValidations.isStandardLicensePlateValid(carToUpdate.getLicensePlate()))
            throw new IllegalArgumentException("Placa do car inválida: " + carToUpdate.getLicensePlate());

        if (!DriverValidations.isChassisValid(carToUpdate.getChassis()))
            throw new IllegalArgumentException("Chassi do car inválido: " + carToUpdate.getChassis());

        if (carRepository.existsByLicensePlate(carToUpdate.getLicensePlate()))
            throw new IllegalArgumentException("Placa do car já existente no sistema: " + carToUpdate.getLicensePlate());

        if (carRepository.existsByChassis(carToUpdate.getChassis()))
            throw new IllegalArgumentException("Número de chassi já existente no sistema: " + carToUpdate.getLicensePlate());


        carToUpdate.setId(id);
        carToUpdate.setAccessories(oldCar.getAccessories());
        CarModel updatedCar = carRepository.save(carToUpdate);

        return modelMapper.map(updatedCar, CarResponseDTO.class);
    }

    @Override
    public List<AccessoryResponseDTO> getCarAccessories(UUID id) {
        CarModel car = carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Carro não encontrado com o ID: " + id));

        List<AccessoryModel> workouts = car.getAccessories();
        List<AccessoryResponseDTO> response = new ArrayList<>();

        workouts.forEach(accessory -> response.add(modelMapper.map(accessory, AccessoryResponseDTO.class)));

        return response;
    }

    @Override
    public List<AccessoryResponseDTO> addAccessory(UUID carId, UUID accessoryId) {
        CarModel car = carRepository.findById(carId).orElseThrow(() -> new IllegalArgumentException("Carro não encontrado com o ID: " + carId));

        AccessoryModel accessory = accessoryRepository.findById(accessoryId).orElseThrow(() -> new IllegalArgumentException("Acessório não encontrado com o ID: " + accessoryId));

        car.getAccessories().add(accessory);
        carRepository.save(car);

        List<AccessoryResponseDTO> response = new ArrayList<>();
        car.getAccessories().forEach(acc -> response.add(modelMapper.map(acc, AccessoryResponseDTO.class)));
        return response;
    }

    @Override
    public List<AccessoryResponseDTO> removeAccessory(UUID carId, UUID accessoryId) {
        CarModel car = carRepository.findById(carId).orElseThrow(() -> new IllegalArgumentException("Carro não encontrado com o ID: " + carId));

        AccessoryModel accessory = accessoryRepository.findById(accessoryId).orElseThrow(() -> new IllegalArgumentException("Acessório não encontrado com o ID: " + accessoryId));

        if (car.getAccessories().remove(accessory))
            throw new IllegalArgumentException("Acessório não encontrado no carro com o ID: " + carId);

        carRepository.save(car);

        List<AccessoryResponseDTO> response = new ArrayList<>();
        car.getAccessories().forEach(acc -> response.add(modelMapper.map(acc, AccessoryResponseDTO.class)));
        return response;
    }
}
