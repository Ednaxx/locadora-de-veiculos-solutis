package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.squad9.vehiclerentalservice.dto.request.CarRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.CarResponseDTO;
import org.squad9.vehiclerentalservice.model.AccessoryModel;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.CarTypeModel;
import org.squad9.vehiclerentalservice.repository.CarRepository;
import org.squad9.vehiclerentalservice.service.CarServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CarServiceImpl carService;

    private CarRequestDTO carRequestDTO;
    private CarModel carModel;
    private CarResponseDTO carResponseDTO;

    @BeforeEach
    void setUp() {
        carRequestDTO = new CarRequestDTO();
        carRequestDTO.setLicensePlate("ABC-1234");
        carRequestDTO.setChassis("1HGCM82633A123456");

        carModel = new CarModel();
        carModel.setId(UUID.randomUUID());
        carModel.setLicensePlate(carRequestDTO.getLicensePlate());
        carModel.setChassis(carRequestDTO.getChassis());

        carResponseDTO = new CarResponseDTO();
        carResponseDTO.setId(carModel.getId());
        carResponseDTO.setLicensePlate(carModel.getLicensePlate());
        carResponseDTO.setChassis(carModel.getChassis());
    }

    @Test
    void testSave() {
        when(modelMapper.map(carRequestDTO, CarModel.class)).thenReturn(carModel);
        when(modelMapper.map(carModel, CarResponseDTO.class)).thenReturn(carResponseDTO);
        when(carRepository.save(any(CarModel.class))).thenReturn(carModel);

        CarResponseDTO result = carService.save(carRequestDTO);
        assertNotNull(result);
        assertEquals(carModel.getLicensePlate(), result.getLicensePlate());
        assertEquals(carModel.getChassis(), result.getChassis());

        verify(carRepository, times(1)).save(any(CarModel.class));
    }

    @Test
    void testFindAll() {
        when(carRepository.findAll()).thenReturn(Collections.singletonList(carModel));
        when(modelMapper.map(carModel, CarResponseDTO.class)).thenReturn(carResponseDTO);

        List<CarResponseDTO> result = carService.findAll();
        assertEquals(1, result.size());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        UUID id = carModel.getId();
        when(carRepository.findById(id)).thenReturn(Optional.of(carModel));
        when(modelMapper.map(carModel, CarResponseDTO.class)).thenReturn(carResponseDTO);

        CarResponseDTO result = carService.findById(id);
        assertNotNull(result);
        assertEquals(carModel.getId(), result.getId());

        verify(carRepository, times(1)).findById(id);
    }

    @Test
    void testFindByCarType() {
        CarTypeModel carType = new CarTypeModel();
        when(carRepository.findByCarModelId(carType.getId())).thenReturn(Collections.singletonList(carModel));
        when(modelMapper.map(carModel, CarResponseDTO.class)).thenReturn(carResponseDTO);

        List<CarResponseDTO> result = carService.findByCarModelId(carType.getId());
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(carRepository, times(1)).findByCarModelId(carType.getId());
    }

    @Test
    void testFindByAccessory() {
        AccessoryModel accessory = new AccessoryModel();
        when(carRepository.findByAccessoriesId(accessory.getId())).thenReturn(Collections.singletonList(carModel));
        when(modelMapper.map(carModel, CarResponseDTO.class)).thenReturn(carResponseDTO);

        List<CarResponseDTO> result = carService.findByAccessoryId(accessory.getId());
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(carRepository, times(1)).findByAccessoriesId(accessory.getId());
    }
}
