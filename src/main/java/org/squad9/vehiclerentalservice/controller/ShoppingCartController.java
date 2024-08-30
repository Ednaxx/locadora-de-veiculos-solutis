package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.dto.AddCartDTO;
import org.squad9.vehiclerentalservice.dto.BookingDetailsDTO;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.DriverModel;
import org.squad9.vehiclerentalservice.model.ShoppingCartModel;
import org.squad9.vehiclerentalservice.service.CarServiceImpl;
import org.squad9.vehiclerentalservice.service.DriverServiceImpl;
import org.squad9.vehiclerentalservice.service.ShoppingCartServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/carrinhos-compras")
@RequiredArgsConstructor
//TODO: Terminar essa parte e revisitar essa logica pq ta muito estranha
public class ShoppingCartController {
    private final ShoppingCartServiceImpl shoppingCartService;
    private final DriverServiceImpl driverService;
    private final CarServiceImpl carService;

    @GetMapping
    public ResponseEntity<List<ShoppingCartModel>> findAll() {
        List<ShoppingCartModel> shoppingCarts = shoppingCartService.findAll();
        return ResponseEntity.ok(shoppingCarts);
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<ShoppingCartModel> findCartByDriverEmail(@PathVariable String email) {
        DriverModel driver = driverService.findByEmail(email);
        if (driver == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        ShoppingCartModel shoppingCart = shoppingCartService.findByMotorista(driver);
        if (shoppingCart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(shoppingCart);
    }

    @PostMapping(value = "/adicionar-ao-carrinho")
    public ResponseEntity<ShoppingCartModel> addToCart(@RequestBody AddCartDTO addCartDTO) {
        DriverModel driver = driverService.findByEmail(addCartDTO.getEmail());
        if (driver == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        ShoppingCartModel shoppingCart = shoppingCartService.findByMotorista(driver);
        if (shoppingCart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        CarModel car = carService.findById(addCartDTO.getId());
        if (car == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        ShoppingCartModel updatedCart = shoppingCartService.addCarros(shoppingCart, car);
        return ResponseEntity.ok(updatedCart);
    }


    @PostMapping(value = "/detalhes-da-reserva")
    public ResponseEntity<BookingDetailsDTO> getBookingDetails(@RequestBody BookingDetailsDTO bookingDetailsDTO) {
        String email = bookingDetailsDTO.getEmailMotorista();
        DriverModel driver = driverService.findByEmail(email);
        if (driver == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        bookingDetailsDTO.setMotorista(driver);
        return ResponseEntity.ok(bookingDetailsDTO);
    }

    @GetMapping(value = "/carrinho/{carrinhoId}")
    public ResponseEntity<List<CarModel>> findCarsInCart(@PathVariable UUID carrinhoId) {
        ShoppingCartModel shoppingCart = shoppingCartService.findById(carrinhoId);
        if (shoppingCart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<CarModel> carrosNoCarrinho = shoppingCartService.getCarrosByCarrinhoId(carrinhoId);
        return ResponseEntity.ok(carrosNoCarrinho);
    }

    @GetMapping(value = "/carrinho/{carrinhoId}/{carroId}")
    public ResponseEntity<?> findByCarId(@PathVariable UUID shoppingCartId, @PathVariable UUID carId) {
        try {
            ShoppingCartModel shoppingCart = shoppingCartService.findById(shoppingCartId);

            if (shoppingCart == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            CarModel car = carService.findById(carId);
            if (car == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            return ResponseEntity.ok(shoppingCartService.findByCarroId(shoppingCart, car));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar carros no carrinho: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/carrinho/{carrinhoId}/{carroId}")
    public ResponseEntity<String> removeCarById(@PathVariable UUID carrinhoId, @PathVariable UUID carroId) {
        try {
            ShoppingCartModel shoppingCart = shoppingCartService.findById(carrinhoId);
            CarModel carro = carService.findById(carroId);

            shoppingCartService.removerCarro(shoppingCart, carro);
            return ResponseEntity.ok("Carro removido do carrinho com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao remover car do carrinho: " + e.getMessage());
        }
    }
}
