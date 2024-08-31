package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.dto.request.AccessoryRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.AccessoryResponseDTO;
import org.squad9.vehiclerentalservice.model.AccessoryModel;
import org.squad9.vehiclerentalservice.repository.AccessoryRepository;
import org.squad9.vehiclerentalservice.service.interfaces.AccessoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccessoryServiceImpl implements AccessoryService {
    private final AccessoryRepository accessoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AccessoryResponseDTO> findAll() {
        List<AccessoryModel> accessories = accessoryRepository.findAll();
        List<AccessoryResponseDTO> response = new ArrayList<>();

        accessories.forEach(accessory -> response.add(modelMapper.map(accessory, AccessoryResponseDTO.class)));
        return response;
    }

    @Override
    public AccessoryResponseDTO findById(UUID id) {
        AccessoryModel accessory = accessoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Acessório não encontrado com o ID: " + id));

        return modelMapper.map(accessory, AccessoryResponseDTO.class);
    }

    @Override
    public AccessoryResponseDTO save(AccessoryRequestDTO request) {
        AccessoryModel accessoryToSave = modelMapper.map(request, AccessoryModel.class);
        AccessoryModel savedAccessory = accessoryRepository.save(accessoryToSave);

        return modelMapper.map(savedAccessory, AccessoryResponseDTO.class);
    }

    @Override
    public void remove(UUID id) {
        if (!accessoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Acessório não encontrado com o ID: " + id);
        }
        accessoryRepository.deleteById(id);
    }

    @Override
    public AccessoryResponseDTO update(UUID id, AccessoryRequestDTO request) {
        accessoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Acessório não encontrado com o ID: " + id));

        AccessoryModel accessoryToUpdate = modelMapper.map(request, AccessoryModel.class);
        accessoryToUpdate.setId(id);
        AccessoryModel updatedAccessory = accessoryRepository.save(accessoryToUpdate);

        return modelMapper.map(updatedAccessory, AccessoryResponseDTO.class);
    }
}
