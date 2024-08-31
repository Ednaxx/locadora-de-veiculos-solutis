package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.dto.request.CarTypeRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.CarTypeResponseDTO;
import org.squad9.vehiclerentalservice.model.CarTypeModel;
import org.squad9.vehiclerentalservice.model.util.Category;
import org.squad9.vehiclerentalservice.repository.CarTypeRepository;
import org.squad9.vehiclerentalservice.service.interfaces.CarTypeService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarTypeServiceImpl implements CarTypeService {
    private final CarTypeRepository carTypeRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CarTypeResponseDTO> findAll() {
        List<CarTypeModel> carModels = carTypeRepository.findAll();
        List<CarTypeResponseDTO> response = new ArrayList<>();

        carModels.forEach(carModel -> response.add(modelMapper.map(carModel, CarTypeResponseDTO.class)));
        return response;
    }

    @Override
    public CarTypeResponseDTO findById(UUID id) {
        CarTypeModel carModel = carTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Acessório não encontrado com o ID: " + id));

        return modelMapper.map(carModel, CarTypeResponseDTO.class);
    }

    @Override
    public List<CarTypeResponseDTO> findByCategoria(Category category) {
        List<CarTypeModel> carModels = carTypeRepository.findByCategory(category);
        List<CarTypeResponseDTO> response = new ArrayList<>();

        carModels.forEach(carModel -> response.add(modelMapper.map(carModel, CarTypeResponseDTO.class)));
        return response;
    }

    @Override
    public CarTypeResponseDTO save(CarTypeRequestDTO request) {
        CarTypeModel carModelToSave = modelMapper.map(request, CarTypeModel.class);
        CarTypeModel savedCarModel = carTypeRepository.save(carModelToSave);

        return modelMapper.map(savedCarModel, CarTypeResponseDTO.class);
    }

    @Override
    public void remove(UUID id) {
        if (!carTypeRepository.existsById(id)) {
            throw new IllegalArgumentException("Modelo de carro não encontrado com o ID: " + id);
        }
        carTypeRepository.deleteById(id);
    }

    @Override
    public CarTypeResponseDTO update(UUID id, CarTypeRequestDTO request) {
        carTypeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Acessório não encontrado com o ID: " + id));

        CarTypeModel carModelToUpdate = modelMapper.map(request, CarTypeModel.class);
        carModelToUpdate.setId(id);
        CarTypeModel updatedCarModel = carTypeRepository.save(carModelToUpdate);

        return modelMapper.map(updatedCarModel, CarTypeResponseDTO.class);
    }
}
