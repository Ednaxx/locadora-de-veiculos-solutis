package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.squad9.vehiclerentalservice.dto.response.CarResponseDTO;
import org.squad9.vehiclerentalservice.dto.response.ShoppingCartResponseDTO;
import org.squad9.vehiclerentalservice.exception.RestException;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.DriverModel;
import org.squad9.vehiclerentalservice.model.ShoppingCartModel;
import org.squad9.vehiclerentalservice.repository.CarRepository;
import org.squad9.vehiclerentalservice.repository.ShoppingCartRepository;
import org.squad9.vehiclerentalservice.service.ShoppingCartServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShoppingCartServiceImplTest {

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private CarRepository carRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService;

    private ShoppingCartModel shoppingCartModel;
    private ShoppingCartResponseDTO shoppingCartResponseDTO;
    private CarModel carModel;
    private CarResponseDTO carResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize the models and DTOs
        shoppingCartModel = new ShoppingCartModel();
        shoppingCartResponseDTO = new ShoppingCartResponseDTO();
        carModel = new CarModel();
        carResponseDTO = new CarResponseDTO();
    }

    @Test
    void testFindAll_whenCarrinhoNotEmpty() {
        List<ShoppingCartModel> carrinhoList = new ArrayList<>();
        carrinhoList.add(shoppingCartModel);
        when(shoppingCartRepository.findAll()).thenReturn(carrinhoList);
        when(modelMapper.map(shoppingCartModel, ShoppingCartResponseDTO.class)).thenReturn(shoppingCartResponseDTO);

        List<ShoppingCartResponseDTO> result = shoppingCartService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(shoppingCartRepository, times(1)).findAll();
    }

    @Test
    void testFindAll_whenCarrinhoIsEmpty() {
        when(shoppingCartRepository.findAll()).thenReturn(new ArrayList<>());

        List<ShoppingCartResponseDTO> result = shoppingCartService.findAll();

        assertNotNull(result);  // Changed from assertNull to assertNotNull
        assertTrue(result.isEmpty());
        verify(shoppingCartRepository, times(1)).findAll();
    }

    @Test
    void testAddCarros() {
        shoppingCartModel.setCars(new ArrayList<>());

        carModel.setId(UUID.randomUUID());

        when(shoppingCartRepository.findById(shoppingCartModel.getId())).thenReturn(Optional.of(shoppingCartModel));
        when(carRepository.findById(carModel.getId())).thenReturn(Optional.of(carModel));
        when(shoppingCartRepository.save(any(ShoppingCartModel.class))).thenReturn(shoppingCartModel);
        when(modelMapper.map(carModel, CarResponseDTO.class)).thenReturn(carResponseDTO);

        List<CarResponseDTO> result = shoppingCartService.addCarToShoppingCart(shoppingCartModel.getId(), carModel.getId());

        assertNotNull(result);
        verify(shoppingCartRepository, times(1)).save(shoppingCartModel);
        verify(carRepository, times(1)).findById(carModel.getId());
    }

    @Test
    void testFindByMotorista() {
        DriverModel motorista = new DriverModel();
        motorista.setEmail("test@test.com");

        when(shoppingCartRepository.findByDriverEmail(motorista.getEmail())).thenReturn(Optional.of(shoppingCartModel));
        when(modelMapper.map(shoppingCartModel, ShoppingCartResponseDTO.class)).thenReturn(shoppingCartResponseDTO);

        ShoppingCartResponseDTO result = shoppingCartService.findByDriverEmail(motorista.getEmail());

        assertNotNull(result);
        verify(shoppingCartRepository, times(1)).findByDriverEmail(motorista.getEmail());
    }

    @Test
    void testFindById_whenCarrinhoExists() {
        UUID carrinhoId = UUID.randomUUID();

        when(shoppingCartRepository.findById(carrinhoId)).thenReturn(Optional.of(shoppingCartModel));
        when(modelMapper.map(shoppingCartModel, ShoppingCartResponseDTO.class)).thenReturn(shoppingCartResponseDTO);

        ShoppingCartResponseDTO result = shoppingCartService.findById(carrinhoId);

        assertNotNull(result);
        verify(shoppingCartRepository, times(1)).findById(carrinhoId);
    }

    @Test
    void testFindById_whenCarrinhoDoesNotExist() {
        UUID carrinhoId = UUID.randomUUID();
        when(shoppingCartRepository.findById(carrinhoId)).thenReturn(Optional.empty());

        // Expecting the IllegalArgumentException to be thrown
        assertThrows(RestException.class, () -> {
            shoppingCartService.findById(carrinhoId);
        });

        verify(shoppingCartRepository, times(1)).findById(carrinhoId);
    }
}
