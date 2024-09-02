package org.squad9.vehiclerentalservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.squad9.vehiclerentalservice.dto.request.AccessoryRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.AccessoryResponseDTO;
import org.squad9.vehiclerentalservice.model.util.ApiResponse;
import org.squad9.vehiclerentalservice.service.AccessoryServiceImpl;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AcessoryControllerTest {

    @Mock
    private AccessoryServiceImpl accessoryService;

    @InjectMocks
    private AccessoryController accessoryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() throws Exception {
        List<AccessoryResponseDTO> accessories = Arrays.asList(new AccessoryResponseDTO(), new AccessoryResponseDTO());
        when(accessoryService.findAll()).thenReturn(accessories);

        Method method = AccessoryController.class.getDeclaredMethod("findAll");
        method.setAccessible(true);
        ResponseEntity<List<AccessoryResponseDTO>> response = (ResponseEntity<List<AccessoryResponseDTO>>) method.invoke(accessoryController);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accessories, response.getBody());
    }

    @Test
    void testFindById() throws Exception {
        UUID id = UUID.randomUUID();
        AccessoryResponseDTO accessory = new AccessoryResponseDTO();
        when(accessoryService.findById(id)).thenReturn(accessory);

        Method method = AccessoryController.class.getDeclaredMethod("findById", UUID.class);
        method.setAccessible(true);
        ResponseEntity<AccessoryResponseDTO> response = (ResponseEntity<AccessoryResponseDTO>) method.invoke(accessoryController, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accessory, response.getBody());
    }

    @Test
    void testCreate() throws Exception {
        AccessoryRequestDTO request = new AccessoryRequestDTO();
        AccessoryResponseDTO responseDTO = new AccessoryResponseDTO();
        when(accessoryService.save(request)).thenReturn(responseDTO);

        Method method = AccessoryController.class.getDeclaredMethod("create", AccessoryRequestDTO.class);
        method.setAccessible(true);
        ResponseEntity<AccessoryResponseDTO> response = (ResponseEntity<AccessoryResponseDTO>) method.invoke(accessoryController, request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

    @Test
    void testDelete() throws Exception {
        UUID id = UUID.randomUUID();
        doNothing().when(accessoryService).remove(id);

        Method method = AccessoryController.class.getDeclaredMethod("delete", UUID.class);
        method.setAccessible(true);
        ResponseEntity<ApiResponse> response = (ResponseEntity<ApiResponse>) method.invoke(accessoryController, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("ID: " + id + " apagado com sucesso", response.getBody().getMessage());
    }

    @Test
    void testUpdate() throws Exception {
        UUID id = UUID.randomUUID();
        AccessoryRequestDTO request = new AccessoryRequestDTO();
        AccessoryResponseDTO responseDTO = new AccessoryResponseDTO();
        when(accessoryService.update(id, request)).thenReturn(responseDTO);

        Method method = AccessoryController.class.getDeclaredMethod("update", UUID.class, AccessoryRequestDTO.class);
        method.setAccessible(true);
        ResponseEntity<AccessoryResponseDTO> response = (ResponseEntity<AccessoryResponseDTO>) method.invoke(accessoryController, id, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }
}
