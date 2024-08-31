package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.DriverModel;
import org.squad9.vehiclerentalservice.model.ShoppingCartModel;
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

    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll_whenCarrinhoNotEmpty() {

        List<ShoppingCartModel> carrinhoList = new ArrayList<>();
        carrinhoList.add(new ShoppingCartModel());
        when(shoppingCartRepository.findAll()).thenReturn(carrinhoList);


        List<ShoppingCartModel> result = shoppingCartService.findAll();


        assertNotNull(result);
        assertEquals(1, result.size());
        verify(shoppingCartRepository, times(1)).findAll();
    }

    @Test
    void testFindAll_whenCarrinhoIsEmpty() {

        when(shoppingCartRepository.findAll()).thenReturn(new ArrayList<>());


        List<ShoppingCartModel> result = shoppingCartService.findAll();


        assertNull(result);
        verify(shoppingCartRepository, times(1)).findAll();
    }

    @Test
    void testSave() {

        ShoppingCartModel carrinhoCompra = new ShoppingCartModel();
        when(shoppingCartRepository.save(carrinhoCompra)).thenReturn(carrinhoCompra);


        ShoppingCartModel result = shoppingCartService.save(carrinhoCompra);


        assertNotNull(result);
        verify(shoppingCartRepository, times(1)).save(carrinhoCompra);
    }

    @Test
    void testAddCarros() {

        ShoppingCartModel carrinhoCompra = new ShoppingCartModel();
        carrinhoCompra.setCarList(new ArrayList<>());
        CarModel carro = new CarModel();

        when(shoppingCartRepository.save(carrinhoCompra)).thenReturn(carrinhoCompra);


        ShoppingCartModel result = shoppingCartService.addCarros(carrinhoCompra, carro);


        assertNotNull(result);
        assertTrue(result.getCarList().contains(carro));
        verify(shoppingCartRepository, times(1)).save(carrinhoCompra);
    }

    @Test
    void testFindByMotorista() {

        DriverModel motorista = new DriverModel();
        ShoppingCartModel carrinho = new ShoppingCartModel();
        when(shoppingCartRepository.findByDriver(motorista)).thenReturn(carrinho);


        ShoppingCartModel result = shoppingCartService.findByMotorista(motorista);


        assertNotNull(result);
        verify(shoppingCartRepository, times(1)).findByDriver(motorista);
    }

    @Test
    void testFindById_whenCarrinhoExists() {

        UUID carrinhoId = UUID.randomUUID();
        ShoppingCartModel carrinho = new ShoppingCartModel();
        when(shoppingCartRepository.findById(carrinhoId)).thenReturn(Optional.of(carrinho));


        ShoppingCartModel result = shoppingCartService.findById(carrinhoId);


        assertNotNull(result);
        verify(shoppingCartRepository, times(1)).findById(carrinhoId);
    }

    @Test
    void testFindById_whenCarrinhoDoesNotExist() {

        UUID carrinhoId = UUID.randomUUID();
        when(shoppingCartRepository.findById(carrinhoId)).thenReturn(Optional.empty());


        ShoppingCartModel result = shoppingCartService.findById(carrinhoId);


        assertNull(result);
        verify(shoppingCartRepository, times(1)).findById(carrinhoId);
    }
}
