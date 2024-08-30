package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.dto.request.CarModelRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.AccessoryResponseDTO;
import org.squad9.vehiclerentalservice.dto.response.CarModelResponseDTO;
import org.squad9.vehiclerentalservice.model.AccessoryModel;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.CarModelModel;
import org.squad9.vehiclerentalservice.model.util.Category;
import org.squad9.vehiclerentalservice.repository.CarModelRepository;
import org.squad9.vehiclerentalservice.service.interfaces.CarModelService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarModelServiceImpl implements CarModelService {
    private final CarModelRepository carModelRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CarModelResponseDTO> findAll() {
        List<CarModelModel> carModels = carModelRepository.findAll();
        List<CarModelResponseDTO> response = new ArrayList<>();

        carModels.forEach(carModel -> response.add(modelMapper.map(carModel, CarModelResponseDTO.class)));
        return response;
    }

    @Override
    public CarModelResponseDTO findById(UUID id) {
        CarModelModel carModel = carModelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Acessório não encontrado com o ID: " + id));

        return modelMapper.map(carModel, CarModelResponseDTO.class);
    }

    @Override
    public List<CarModelResponseDTO> findByCategoria(Category category) {
        List<CarModelModel> carModels = carModelRepository.findByCategory(category);
        List<CarModelResponseDTO> response = new ArrayList<>();

        carModels.forEach(carModel -> response.add(modelMapper.map(carModel, CarModelResponseDTO.class)));
        return response;
    }

    @Override
    public CarModelResponseDTO save(CarModelRequestDTO request) {
        CarModelModel carModelToSave = modelMapper.map(request, CarModelModel.class);
        CarModelModel savedCarModel = carModelRepository.save(carModelToSave);

        return modelMapper.map(savedCarModel, CarModelResponseDTO.class);
    }

    @Override
    public void remove(UUID id) {
        if (!carModelRepository.existsById(id)) {
            throw new IllegalArgumentException("Modelo de carro não encontrado com o ID: " + id);
        }
        carModelRepository.deleteById(id);
    }

    @Override
    public CarModelResponseDTO update(UUID id, CarModelRequestDTO request) {
        carModelRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Acessório não encontrado com o ID: " + id));

        CarModelModel carModelToUpdate = modelMapper.map(request, CarModelModel.class);
        carModelToUpdate.setId(id);
        CarModelModel updatedCarModel = carModelRepository.save(carModelToUpdate);

        return modelMapper.map(updatedCarModel, CarModelResponseDTO.class);
    }
}
