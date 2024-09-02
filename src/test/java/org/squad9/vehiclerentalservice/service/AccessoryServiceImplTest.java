package org.squad9.vehiclerentalservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.squad9.vehiclerentalservice.dto.request.AccessoryRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.AccessoryResponseDTO;
import org.squad9.vehiclerentalservice.model.AccessoryModel;
import org.squad9.vehiclerentalservice.repository.AccessoryRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccessoryServiceImplTest {

    @Mock
    private AccessoryRepository accessoryRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AccessoryServiceImpl accessoryService;

    private AccessoryRequestDTO accessoryRequestDTO;
    private AccessoryModel accessoryModel;
    private AccessoryResponseDTO accessoryResponseDTO;

    @BeforeEach
    void setUp() {
        accessoryRequestDTO = new AccessoryRequestDTO();

        accessoryModel = new AccessoryModel();
        accessoryModel.setId(UUID.randomUUID());

        accessoryResponseDTO = new AccessoryResponseDTO();
        accessoryResponseDTO.setId(accessoryModel.getId());
    }

    @Test
    void testFindAll() {
        when(accessoryRepository.findAll()).thenReturn(Collections.singletonList(accessoryModel));
        when(modelMapper.map(accessoryModel, AccessoryResponseDTO.class)).thenReturn(accessoryResponseDTO);

        List<AccessoryResponseDTO> result = accessoryService.findAll();
        assertEquals(1, result.size());
        verify(accessoryRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        UUID id = accessoryModel.getId();
        when(accessoryRepository.findById(id)).thenReturn(Optional.of(accessoryModel));
        when(modelMapper.map(accessoryModel, AccessoryResponseDTO.class)).thenReturn(accessoryResponseDTO);

        AccessoryResponseDTO result = accessoryService.findById(id);
        assertNotNull(result);
        assertEquals(accessoryModel.getId(), result.getId());

        verify(accessoryRepository, times(1)).findById(id);
    }

    @Test
    void testSave() {
        when(accessoryRepository.save(any(AccessoryModel.class))).thenReturn(accessoryModel);
        when(modelMapper.map(accessoryRequestDTO, AccessoryModel.class)).thenReturn(accessoryModel);
        when(modelMapper.map(accessoryModel, AccessoryResponseDTO.class)).thenReturn(accessoryResponseDTO);

        AccessoryResponseDTO result = accessoryService.save(accessoryRequestDTO);
        assertNotNull(result);
        assertEquals(accessoryModel.getId(), result.getId());

        verify(accessoryRepository, times(1)).save(any(AccessoryModel.class));
    }

    @Test
    void testRemove() {
        UUID id = accessoryModel.getId();
        when(accessoryRepository.existsById(id)).thenReturn(true);
        doNothing().when(accessoryRepository).deleteById(id);

        accessoryService.remove(id);

        verify(accessoryRepository, times(1)).deleteById(id);
    }
}
