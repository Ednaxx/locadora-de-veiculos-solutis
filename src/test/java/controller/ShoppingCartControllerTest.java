package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.squad9.vehiclerentalservice.dto.response.CarResponseDTO;
import org.squad9.vehiclerentalservice.dto.response.ShoppingCartResponseDTO;
import org.squad9.vehiclerentalservice.service.ShoppingCartServiceImpl;
import org.squad9.vehiclerentalservice.controller.ShoppingCartController;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ShoppingCartControllerTest {

    @Mock
    private ShoppingCartServiceImpl shoppingCartService;

    @InjectMocks
    private ShoppingCartController shoppingCartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() throws Exception {
        List<ShoppingCartResponseDTO> shoppingCarts = Arrays.asList(new ShoppingCartResponseDTO(), new ShoppingCartResponseDTO());
        when(shoppingCartService.findAll()).thenReturn(shoppingCarts);

        Method method = ShoppingCartController.class.getDeclaredMethod("findAll");
        method.setAccessible(true);
        ResponseEntity<List<ShoppingCartResponseDTO>> response = (ResponseEntity<List<ShoppingCartResponseDTO>>) method.invoke(shoppingCartController);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shoppingCarts, response.getBody());
    }

    @Test
    void testFindById() throws Exception {
        UUID id = UUID.randomUUID();
        ShoppingCartResponseDTO shoppingCart = new ShoppingCartResponseDTO();
        when(shoppingCartService.findById(id)).thenReturn(shoppingCart);

        Method method = ShoppingCartController.class.getDeclaredMethod("findById", UUID.class);
        method.setAccessible(true);
        ResponseEntity<ShoppingCartResponseDTO> response = (ResponseEntity<ShoppingCartResponseDTO>) method.invoke(shoppingCartController, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shoppingCart, response.getBody());
    }

    @Test
    void testFindByDriverEmail() throws Exception {
        String email = "driver@example.com";
        ShoppingCartResponseDTO shoppingCart = new ShoppingCartResponseDTO();
        when(shoppingCartService.findByDriverEmail(email)).thenReturn(shoppingCart);

        Method method = ShoppingCartController.class.getDeclaredMethod("findByDriverEmail", String.class);
        method.setAccessible(true);
        ResponseEntity<ShoppingCartResponseDTO> response = (ResponseEntity<ShoppingCartResponseDTO>) method.invoke(shoppingCartController, email);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shoppingCart, response.getBody());
    }

    @Test
    void testFindShoppingCartsCars() throws Exception {
        UUID id = UUID.randomUUID();
        List<CarResponseDTO> cars = Arrays.asList(new CarResponseDTO(), new CarResponseDTO());
        when(shoppingCartService.findShoppingCartsCars(id)).thenReturn(cars);

        Method method = ShoppingCartController.class.getDeclaredMethod("findShoppingCartsCars", UUID.class);
        method.setAccessible(true);
        ResponseEntity<List<CarResponseDTO>> response = (ResponseEntity<List<CarResponseDTO>>) method.invoke(shoppingCartController, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }

    @Test
    void testAddCarToShoppingCart() throws Exception {
        UUID id = UUID.randomUUID();
        UUID idCarro = UUID.randomUUID();
        List<CarResponseDTO> cars = Arrays.asList(new CarResponseDTO(), new CarResponseDTO());
        when(shoppingCartService.addCarToShoppingCart(id, idCarro)).thenReturn(cars);

        Method method = ShoppingCartController.class.getDeclaredMethod("addCarToShoppingCart", UUID.class, UUID.class);
        method.setAccessible(true);
        ResponseEntity<List<CarResponseDTO>> response = (ResponseEntity<List<CarResponseDTO>>) method.invoke(shoppingCartController, id, idCarro);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }

    @Test
    void testRemoveCarFromShoppingCart() throws Exception {
        UUID id = UUID.randomUUID();
        UUID idCarro = UUID.randomUUID();
        List<CarResponseDTO> cars = Arrays.asList(new CarResponseDTO(), new CarResponseDTO());
        when(shoppingCartService.removeCarFromShoppingCart(id, idCarro)).thenReturn(cars);

        Method method = ShoppingCartController.class.getDeclaredMethod("removeCarFromShoppingCart", UUID.class, UUID.class);
        method.setAccessible(true);
        ResponseEntity<List<CarResponseDTO>> response = (ResponseEntity<List<CarResponseDTO>>) method.invoke(shoppingCartController, id, idCarro);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars, response.getBody());
    }
}
