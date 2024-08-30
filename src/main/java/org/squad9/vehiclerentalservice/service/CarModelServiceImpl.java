package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.squad9.vehiclerentalservice.model.CarModelModel;
import org.squad9.vehiclerentalservice.model.util.Category;
import org.squad9.vehiclerentalservice.repository.CarModelRepository;
import org.squad9.vehiclerentalservice.service.interfaces.CarModelService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarModelServiceImpl implements CarModelService {
    private CarModelRepository carModelRepository;

    @Override
    public List<CarModelModel> findAll() {
        try {
            return carModelRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível encontrar registros de modelos de carro: " + e.getMessage());
        }
    }

    @Override
    public CarModelModel findById(UUID id) {
        try {
            return carModelRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Modelo de carro não encontrado com o ID: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar modelo de carro por ID: " + e.getMessage());
        }
    }

    @Override
    public List<CarModelModel> findByCategoria(Category category) {
        try {
            return carModelRepository.findByCategory(category);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar modelos de carro por categoria: " + e.getMessage());
        }
    }

    @Override
    public CarModelModel save(CarModelModel modeloCarro) {
        try {
            return carModelRepository.save(modeloCarro);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível salvar o modelo de carro: " + e.getMessage());
        }
    }

    @Override
    public void remove(UUID id) {
        try {
            carModelRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover modelo de carro: " + e.getMessage());
        }
    }

    @Override
    public CarModelModel update(UUID id, CarModelModel carModel) {
        try {
            CarModelModel existingCarModel = carModelRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Modelo de carro não encontrado com o ID: " + id));

            existingCarModel.setDescription(carModel.getDescription());
            existingCarModel.setCategory(carModel.getCategory());
            existingCarModel.setCar(carModel.getCar());
            existingCarModel.setManufacturer(carModel.getManufacturer());

            return carModelRepository.save(existingCarModel);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o modelo de carro: " + e.getMessage());
        }
    }
}
