package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.dto.request.ManufacturerRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.ManufacturerResponseDTO;
import org.squad9.vehiclerentalservice.exception.RestException;
import org.squad9.vehiclerentalservice.model.ManufacturerModel;
import org.squad9.vehiclerentalservice.repository.ManufacturerRepository;
import org.squad9.vehiclerentalservice.service.interfaces.ManufacturerService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;
    private final ModelMapper modelMapper;

    @Override
    public ManufacturerResponseDTO save(ManufacturerRequestDTO request) {
        if (manufacturerRepository.existsByName(request.getName())) {
            throw new RestException(HttpStatus.CONFLICT, "O Fornecedor com o nome: " + request.getName() + " já existe.");
        }
        ManufacturerModel manufacturerToSave = modelMapper.map(request, ManufacturerModel.class);
        ManufacturerModel savedManufacturer = manufacturerRepository.save(manufacturerToSave);

        return modelMapper.map(savedManufacturer, ManufacturerResponseDTO.class);
    }

    @Override
    public List<ManufacturerResponseDTO> findAll() {
        List<ManufacturerModel> manufacturers = manufacturerRepository.findAll();
        List<ManufacturerResponseDTO> response = new ArrayList<>();

        manufacturers.forEach(manufacturer -> response.add(modelMapper.map(manufacturer, ManufacturerResponseDTO.class)));
        return response;
    }

    @Override
    public ManufacturerResponseDTO findById(UUID id) {
        ManufacturerModel manufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Fabricante não encontrado com o ID: " + id));

        return modelMapper.map(manufacturer, ManufacturerResponseDTO.class);
    }

    @Override
    public void remove(UUID id) {
        if (!manufacturerRepository.existsById(id)) {
            throw new RestException(HttpStatus.NOT_FOUND, "Fabricante não encontrado com o ID: " + id);
        }
        manufacturerRepository.deleteById(id);
    }

    @Override
    public ManufacturerResponseDTO update(UUID id, ManufacturerRequestDTO request) {
        ManufacturerModel oldManufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Fabricante não encontrado com o ID: " + id));

        ManufacturerModel manufacturerToUpdate = modelMapper.map(request, ManufacturerModel.class);
        manufacturerToUpdate.setId(id);
        manufacturerToUpdate.setCarTypes(oldManufacturer.getCarTypes());

        ManufacturerModel updatedManufacturer = manufacturerRepository.save(manufacturerToUpdate);

        return modelMapper.map(updatedManufacturer, ManufacturerResponseDTO.class);
    }

}
