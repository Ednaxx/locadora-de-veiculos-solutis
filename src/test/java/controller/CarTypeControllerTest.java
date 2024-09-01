package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.squad9.vehiclerentalservice.dto.request.CarTypeRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.CarTypeResponseDTO;
import org.squad9.vehiclerentalservice.enums.Category;
import org.squad9.vehiclerentalservice.service.CarTypeServiceImpl;
import org.squad9.vehiclerentalservice.controller.CarTypeController;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CarTypeControllerTest {

    @Mock
    private CarTypeServiceImpl carModelService;

    @InjectMocks
    private CarTypeController carTypeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() throws Exception {
        List<CarTypeResponseDTO> carTypes = Arrays.asList(new CarTypeResponseDTO(), new CarTypeResponseDTO());
        when(carModelService.findAll()).thenReturn(carTypes);

        Method method = CarTypeController.class.getDeclaredMethod("findAll");
        method.setAccessible(true);
        ResponseEntity<List<CarTypeResponseDTO>> response = (ResponseEntity<List<CarTypeResponseDTO>>) method.invoke(carTypeController);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carTypes, response.getBody());
    }

    @Test
    void testFindById() throws Exception {
        UUID id = UUID.randomUUID();
        CarTypeResponseDTO carType = new CarTypeResponseDTO();
        when(carModelService.findById(id)).thenReturn(carType);

        Method method = CarTypeController.class.getDeclaredMethod("findById", UUID.class);
        method.setAccessible(true);
        ResponseEntity<CarTypeResponseDTO> response = (ResponseEntity<CarTypeResponseDTO>) method.invoke(carTypeController, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carType, response.getBody());
    }

    @Test
    void testFindByCategory() throws Exception {
        Category category = Category.LARGE_SEDAN;
        List<CarTypeResponseDTO> carTypes = Arrays.asList(new CarTypeResponseDTO(), new CarTypeResponseDTO());
        when(carModelService.findByCategory(category)).thenReturn(carTypes);

        Method method = CarTypeController.class.getDeclaredMethod("findByCategory", Category.class);
        method.setAccessible(true);
        ResponseEntity<List<CarTypeResponseDTO>> response = (ResponseEntity<List<CarTypeResponseDTO>>) method.invoke(carTypeController, category);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carTypes, response.getBody());
    }

    @Test
    void testCreate() throws Exception {
        CarTypeRequestDTO request = new CarTypeRequestDTO();
        CarTypeResponseDTO responseDTO = new CarTypeResponseDTO();
        when(carModelService.save(request)).thenReturn(responseDTO);

        Method method = CarTypeController.class.getDeclaredMethod("create", CarTypeRequestDTO.class);
        method.setAccessible(true);
        ResponseEntity<CarTypeResponseDTO> response = (ResponseEntity<CarTypeResponseDTO>) method.invoke(carTypeController, request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void testDelete() throws Exception {
        UUID id = UUID.randomUUID();
        doNothing().when(carModelService).remove(id);

        Method method = CarTypeController.class.getDeclaredMethod("delete", UUID.class);
        method.setAccessible(true);
        ResponseEntity<Void> response = (ResponseEntity<Void>) method.invoke(carTypeController, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdate() throws Exception {
        UUID id = UUID.randomUUID();
        CarTypeRequestDTO request = new CarTypeRequestDTO();
        CarTypeResponseDTO responseDTO = new CarTypeResponseDTO();
        when(carModelService.update(id, request)).thenReturn(responseDTO);

        Method method = CarTypeController.class.getDeclaredMethod("update", UUID.class, CarTypeRequestDTO.class);
        method.setAccessible(true);
        ResponseEntity<CarTypeResponseDTO> response = (ResponseEntity<CarTypeResponseDTO>) method.invoke(carTypeController, id, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }
}
