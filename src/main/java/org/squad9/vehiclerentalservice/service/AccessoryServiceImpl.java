package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
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
        List<AccessoryModel> accessoryModels = accessoryRepository.findAll();
        List<AccessoryResponseDTO> response = new ArrayList<>();

        accessoryModels.forEach(accessory -> response.add(modelMapper.map(accessory, AccessoryResponseDTO.class)));
        return response;
    }

    @Override
    public AccessoryModel findById(UUID id) {
        return accessoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Acessório não encontrado com o ID: " + id));
    }

    @Override
    public AccessoryModel save(AccessoryModel accessory) {
        return accessoryRepository.save(accessory);
    }

    @Override
    public void remove(UUID id) {
        if (!accessoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Acessório não encontrado com o ID: " + id);
        }
        accessoryRepository.deleteById(id);
    }

    @Override
    public AccessoryModel update(UUID id, AccessoryModel accessory) {
        AccessoryModel existingAccessory = accessoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Acessório não encontrado com o ID: " + id));

        existingAccessory.setName(accessory.getName());
        existingAccessory.setDescription(accessory.getDescription());

        return accessoryRepository.save(existingAccessory);
    }
}
