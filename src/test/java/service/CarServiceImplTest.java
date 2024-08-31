package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.squad9.vehiclerentalservice.model.AccessoryModel;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.CarModelModel;
import org.squad9.vehiclerentalservice.repository.CarRepository;
import org.squad9.vehiclerentalservice.service.CarServiceImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    private CarModel carModel;

    @BeforeEach
    void setUp() {
        carModel = new CarModel();
        carModel.setId(UUID.randomUUID());
        carModel.setLicensePlate("ABC-1234");
        carModel.setChassi("1HGCM82633A123456");
    }

    @Test
    void testSave() {
        when(carRepository.save(carModel)).thenReturn(carModel);
        CarModel result = carService.save(carModel);
        assertNotNull(result);
        assertEquals(carModel, result);
        verify(carRepository, times(1)).save(carModel);
    }

    @Test
    void testFindAll() {
        when(carRepository.findAll()).thenReturn(Arrays.asList(carModel));
        List<CarModel> result = carService.findAll();
        assertEquals(1, result.size());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        UUID id = carModel.getId();
        when(carRepository.findById(id)).thenReturn(Optional.of(carModel));
        CarModel result = carService.findById(id);
        assertNotNull(result);
        assertEquals(carModel, result);
        verify(carRepository, times(1)).findById(id);
    }

    @Test
    void testRemove() {
        UUID id = carModel.getId();
        doNothing().when(carRepository).deleteById(id);
        carService.remove(id);
        verify(carRepository, times(1)).deleteById(id);
    }

    @Test
    void testListarCarrosDisponiveis() {
        LocalDate dataInicio = LocalDate.now();
        LocalDate dataDevolucao = dataInicio.plusDays(5);

        CarModel mockCarModel = mock(CarModel.class);
        when(carRepository.findAll()).thenReturn(Arrays.asList(mockCarModel));
        when(mockCarModel.isDisponivelParaAluguel(dataInicio, dataDevolucao)).thenReturn(true);

        List<CarModel> result = carService.listarCarrosDisponiveis(dataInicio, dataDevolucao);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(carRepository, times(1)).findAll();
        verify(mockCarModel, times(1)).isDisponivelParaAluguel(dataInicio, dataDevolucao);
    }


    @Test
    void testFindByModeloCarro() {
        CarModelModel modeloCarro = new CarModelModel();
        when(carRepository.findByCarModel(modeloCarro)).thenReturn(Arrays.asList(carModel));
        List<CarModel> result = carService.findByModeloCarro(modeloCarro);
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(carRepository, times(1)).findByCarModel(modeloCarro);
    }

    @Test
    void testFindByAcessorio() {
        AccessoryModel acessorio = new AccessoryModel();
        when(carRepository.findByAccessoriesContaining(acessorio)).thenReturn(Arrays.asList(carModel));
        List<CarModel> result = carService.findByAcessorio(acessorio);
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(carRepository, times(1)).findByAccessoriesContaining(acessorio);
    }
}
