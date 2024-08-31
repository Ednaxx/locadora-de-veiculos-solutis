package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.squad9.vehiclerentalservice.model.AccessoryModel;
import org.squad9.vehiclerentalservice.repository.AccessoryRepository;
import org.squad9.vehiclerentalservice.service.AccessoryServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AcessoryServiceImplTest {

    @Mock
    private AccessoryRepository accessoryRepository;

    @InjectMocks
    private AccessoryServiceImpl accessoryService;

    private AccessoryModel accessory;

    @BeforeEach
    void setUp() {
        accessory = new AccessoryModel();
        accessory.setId(UUID.randomUUID());
    }

    @Test
    void testFindAll() {
        when(accessoryRepository.findAll()).thenReturn(Arrays.asList(accessory));
        List<AccessoryModel> result = accessoryService.findAll();
        assertEquals(1, result.size());
        verify(accessoryRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        UUID id = accessory.getId();
        when(accessoryRepository.findById(id)).thenReturn(Optional.of(accessory));
        AccessoryModel result = accessoryService.findById(id);
        assertNotNull(result);
        assertEquals(accessory, result);
        verify(accessoryRepository, times(1)).findById(id);
    }

    @Test
    void testSave() {
        when(accessoryRepository.save(accessory)).thenReturn(accessory);
        AccessoryModel result = accessoryService.save(accessory);
        assertNotNull(result);
        assertEquals(accessory, result);
        verify(accessoryRepository, times(1)).save(accessory);
    }

    @Test
    void testRemove() {
        UUID id = accessory.getId();
        when(accessoryRepository.existsById(id)).thenReturn(true);
        doNothing().when(accessoryRepository).deleteById(id);
        accessoryService.remove(id);
        verify(accessoryRepository, times(1)).deleteById(id);
    }
}
