package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.model.CarModelModel;
import org.squad9.vehiclerentalservice.model.util.Category;
import org.squad9.vehiclerentalservice.repository.CarModelRepository;
import org.squad9.vehiclerentalservice.service.interfaces.CarModelService;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarModelServiceImpl implements CarModelService {
    private final CarModelRepository carModelRepository;

    @Override
    public List<CarModelModel> findAll() {
        return carModelRepository.findAll();
    }

    @Override
    public CarModelModel findById(UUID id) {
        return carModelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Modelo de carro não encontrado com o ID: " + id));
    }

    @Override
    public List<CarModelModel> findByCategoria(Category category) {
        return carModelRepository.findByCategory(category);
    }

    @Override
    public CarModelModel save(CarModelModel modeloCarro) {
        return carModelRepository.save(modeloCarro);
    }

    @Override
    public void remove(UUID id) {
        if (!carModelRepository.existsById(id)) {
            throw new IllegalArgumentException("Modelo de carro não encontrado com o ID: " + id);
        }
        carModelRepository.deleteById(id);
    }

    @Override
    public CarModelModel update(UUID id, CarModelModel carModel) {
        CarModelModel existingCarModel = carModelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Modelo de carro não encontrado com o ID: " + id));

        existingCarModel.setDescription(carModel.getDescription());
        existingCarModel.setCategory(carModel.getCategory());
        existingCarModel.setCars(carModel.getCars());
        existingCarModel.setManufacturer(carModel.getManufacturer());

        return carModelRepository.save(existingCarModel);
    }
}
