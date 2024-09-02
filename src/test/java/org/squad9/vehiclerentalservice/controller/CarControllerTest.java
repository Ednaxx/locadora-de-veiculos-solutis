package org.squad9.vehiclerentalservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.squad9.vehiclerentalservice.dto.request.CarRequestDTO;
import org.squad9.vehiclerentalservice.dto.request.DateIntervalRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.AccessoryResponseDTO;
import org.squad9.vehiclerentalservice.dto.response.CarResponseDTO;
import org.squad9.vehiclerentalservice.enums.Category;
import org.squad9.vehiclerentalservice.service.CarServiceImpl;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CarControllerTest {

    @Mock
    private CarServiceImpl carService;

    @InjectMocks
    private CarController carController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() throws Exception {
        List<CarResponseDTO> cars = Arrays.asList(new CarResponseDTO(), new CarResponseDTO());
        when(carService.findAll()).thenReturn(cars);

        Method method = CarController.class.getDeclaredMethod("findAll");
        method.setAccessible(true);
        ResponseEntity<List<CarResponseDTO>> response = (ResponseEntity<List<CarResponseDTO>>) method.invoke(carController);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }

    @Test
    void testFindById() throws Exception {
        UUID id = UUID.randomUUID();
        CarResponseDTO car = new CarResponseDTO();
        when(carService.findById(id)).thenReturn(car);

        Method method = CarController.class.getDeclaredMethod("findById", UUID.class);
        method.setAccessible(true);
        ResponseEntity<CarResponseDTO> response = (ResponseEntity<CarResponseDTO>) method.invoke(carController, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(car, response.getBody());
    }

    @Test
    void testFindAvailableOnDate() throws Exception {
        DateIntervalRequestDTO request = new DateIntervalRequestDTO();
        List<CarResponseDTO> cars = Arrays.asList(new CarResponseDTO(), new CarResponseDTO());
        when(carService.findAvailableOnDate(request)).thenReturn(cars);

        Method method = CarController.class.getDeclaredMethod("findAvailableOnDate", DateIntervalRequestDTO.class);
        method.setAccessible(true);
        ResponseEntity<List<CarResponseDTO>> response = (ResponseEntity<List<CarResponseDTO>>) method.invoke(carController, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }

    @Test
    void testFindByCategory() throws Exception {
        Category category = Category.COMMERCIAL_UTILITY;
        List<CarResponseDTO> cars = Arrays.asList(new CarResponseDTO(), new CarResponseDTO());
        when(carService.findByCategory(category)).thenReturn(cars);

        Method method = CarController.class.getDeclaredMethod("findByCategory", Category.class);
        method.setAccessible(true);
        ResponseEntity<List<CarResponseDTO>> response = (ResponseEntity<List<CarResponseDTO>>) method.invoke(carController, category);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }

    @Test
    void testCreate() throws Exception {
        CarRequestDTO request = new CarRequestDTO();
        CarResponseDTO responseDTO = new CarResponseDTO();
        when(carService.save(request)).thenReturn(responseDTO);

        Method method = CarController.class.getDeclaredMethod("create", CarRequestDTO.class);
        method.setAccessible(true);
        ResponseEntity<CarResponseDTO> response = (ResponseEntity<CarResponseDTO>) method.invoke(carController, request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void testDelete() throws Exception {
        UUID id = UUID.randomUUID();
        doNothing().when(carService).remove(id);

        Method method = CarController.class.getDeclaredMethod("delete", UUID.class);
        method.setAccessible(true);
        ResponseEntity<Void> response = (ResponseEntity<Void>) method.invoke(carController, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdate() throws Exception {
        UUID id = UUID.randomUUID();
        CarRequestDTO request = new CarRequestDTO();
        CarResponseDTO responseDTO = new CarResponseDTO();
        when(carService.update(id, request)).thenReturn(responseDTO);

        Method method = CarController.class.getDeclaredMethod("update", UUID.class, CarRequestDTO.class);
        method.setAccessible(true);
        ResponseEntity<CarResponseDTO> response = (ResponseEntity<CarResponseDTO>) method.invoke(carController, id, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void testGetCarAccessories() throws Exception {
        UUID id = UUID.randomUUID();
        List<AccessoryResponseDTO> accessories = Arrays.asList(new AccessoryResponseDTO(), new AccessoryResponseDTO());
        when(carService.getCarAccessories(id)).thenReturn(accessories);

        Method method = CarController.class.getDeclaredMethod("getCarAccessories", UUID.class);
        method.setAccessible(true);
        ResponseEntity<List<AccessoryResponseDTO>> response = (ResponseEntity<List<AccessoryResponseDTO>>) method.invoke(carController, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accessories, response.getBody());
    }

    @Test
    void testAddAccessory() throws Exception {
        UUID id = UUID.randomUUID();
        UUID accessoryId = UUID.randomUUID();
        List<AccessoryResponseDTO> accessories = Arrays.asList(new AccessoryResponseDTO(), new AccessoryResponseDTO());
        when(carService.addAccessory(id, accessoryId)).thenReturn(accessories);

        Method method = CarController.class.getDeclaredMethod("addAccessory", UUID.class, UUID.class);
        method.setAccessible(true);
        ResponseEntity<List<AccessoryResponseDTO>> response = (ResponseEntity<List<AccessoryResponseDTO>>) method.invoke(carController, id, accessoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accessories, response.getBody());
    }

    @Test
    void testRemoveAccessory() throws Exception {
        UUID id = UUID.randomUUID();
        UUID accessoryId = UUID.randomUUID();
        List<AccessoryResponseDTO> accessories = Arrays.asList(new AccessoryResponseDTO(), new AccessoryResponseDTO());
        when(carService.addAccessory(id, accessoryId)).thenReturn(accessories);

        Method method = CarController.class.getDeclaredMethod("removeAccessory", UUID.class, UUID.class);
        method.setAccessible(true);
        ResponseEntity<List<AccessoryResponseDTO>> response = (ResponseEntity<List<AccessoryResponseDTO>>) method.invoke(carController, id, accessoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accessories, response.getBody());
    }
}
