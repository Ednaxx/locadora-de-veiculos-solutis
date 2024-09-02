package org.squad9.vehiclerentalservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.squad9.vehiclerentalservice.dto.request.CarTypeRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.CarTypeResponseDTO;
import org.squad9.vehiclerentalservice.enums.Category;
import org.squad9.vehiclerentalservice.model.CarTypeModel;
import org.squad9.vehiclerentalservice.model.ManufacturerModel;
import org.squad9.vehiclerentalservice.repository.CarTypeRepository;
import org.squad9.vehiclerentalservice.repository.ManufacturerRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarTypeServiceImplTest {

    @Mock
    private CarTypeRepository carTypeRepository;

    @Mock
    private ManufacturerRepository manufacturerRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CarTypeServiceImpl carTypeService;

    private CarTypeRequestDTO carTypeRequestDTO;
    private CarTypeModel carTypeModel;
    private CarTypeResponseDTO carTypeResponseDTO;
    private ManufacturerModel manufacturerModel;

    @BeforeEach
    void setUp() {
        carTypeRequestDTO = new CarTypeRequestDTO();
        carTypeModel = new CarTypeModel();
        carTypeModel.setId(UUID.randomUUID());
        carTypeResponseDTO = new CarTypeResponseDTO();
        carTypeResponseDTO.setId(carTypeModel.getId());

        manufacturerModel = new ManufacturerModel();
        manufacturerModel.setId(UUID.randomUUID());

        carTypeRequestDTO.setManufacturerId(manufacturerModel.getId());
    }

    @Test
    void testSave() {
        when(modelMapper.map(carTypeRequestDTO, CarTypeModel.class)).thenReturn(carTypeModel);
        when(modelMapper.map(carTypeModel, CarTypeResponseDTO.class)).thenReturn(carTypeResponseDTO);
        when(carTypeRepository.save(any(CarTypeModel.class))).thenReturn(carTypeModel);
        when(manufacturerRepository.findById(carTypeRequestDTO.getManufacturerId())).thenReturn(Optional.of(manufacturerModel));

        CarTypeResponseDTO result = carTypeService.save(carTypeRequestDTO);

        assertNotNull(result);
        assertEquals(carTypeResponseDTO, result);
        verify(carTypeRepository, times(1)).save(any(CarTypeModel.class));
        verify(manufacturerRepository, times(1)).findById(carTypeRequestDTO.getManufacturerId());
    }

    @Test
    void testFindAll() {
        when(carTypeRepository.findAll()).thenReturn(Collections.singletonList(carTypeModel));
        when(modelMapper.map(carTypeModel, CarTypeResponseDTO.class)).thenReturn(carTypeResponseDTO);

        List<CarTypeResponseDTO> result = carTypeService.findAll();
        assertEquals(1, result.size());
        verify(carTypeRepository, times(1)).findAll();
    }

    @Test
    void testFindByCategory() {
        Category category = Category.COMMERCIAL_UTILITY;
        when(carTypeRepository.findByCategory(category)).thenReturn(Collections.singletonList(carTypeModel));
        when(modelMapper.map(carTypeModel, CarTypeResponseDTO.class)).thenReturn(carTypeResponseDTO);

        List<CarTypeResponseDTO> result = carTypeService.findByCategory(category);
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(carTypeRepository, times(1)).findByCategory(category);
    }

    @Test
    void testFindById() {
        UUID id = carTypeModel.getId();
        when(carTypeRepository.findById(id)).thenReturn(Optional.of(carTypeModel));
        when(modelMapper.map(carTypeModel, CarTypeResponseDTO.class)).thenReturn(carTypeResponseDTO);

        CarTypeResponseDTO result = carTypeService.findById(id);
        assertNotNull(result);
        assertEquals(carTypeResponseDTO, result);
        verify(carTypeRepository, times(1)).findById(id);
    }

}
