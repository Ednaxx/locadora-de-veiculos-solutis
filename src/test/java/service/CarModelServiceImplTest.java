package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.squad9.vehiclerentalservice.model.CarModelModel;
import org.squad9.vehiclerentalservice.model.util.Category;
import org.squad9.vehiclerentalservice.repository.CarModelRepository;
import org.squad9.vehiclerentalservice.service.CarModelServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarModelServiceImplTest {

    @Mock
    private CarModelRepository carModelRepository;

    @InjectMocks
    private CarModelServiceImpl carModelService;

    private CarModelModel carModel;

    @BeforeEach
    void setUp() {
        carModel = new CarModelModel();
        carModel.setId(UUID.randomUUID());
    }

    @Test
    void testSave() {
        when(carModelRepository.save(carModel)).thenReturn(carModel);
        CarModelModel result = carModelService.save(carModel);
        assertNotNull(result);
        assertEquals(carModel, result);
        verify(carModelRepository, times(1)).save(carModel);
    }

    @Test
    void testFindAll() {
        when(carModelRepository.findAll()).thenReturn(Arrays.asList(carModel));
        List<CarModelModel> result = carModelService.findAll();
        assertEquals(1, result.size());
        verify(carModelRepository, times(1)).findAll();
    }

    @Test
    void testFindByCategoria() {
        Category category = Category.COMMERCIAL_UTILITY;
        when(carModelRepository.findByCategory(category)).thenReturn(Arrays.asList(carModel));
        List<CarModelModel> result = carModelService.findByCategoria(category);
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(carModelRepository, times(1)).findByCategory(category);
    }

    @Test
    void testFindById() {
        UUID id = carModel.getId();
        when(carModelRepository.findById(id)).thenReturn(Optional.of(carModel));
        CarModelModel result = carModelService.findById(id);
        assertNotNull(result);
        assertEquals(carModel, result);
        verify(carModelRepository, times(1)).findById(id);
    }

    @Test
    void testRemove() {
        UUID id = carModel.getId();
        doNothing().when(carModelRepository).deleteById(id);
        carModelService.remove(id);
        verify(carModelRepository, times(1)).deleteById(id);
    }
}
