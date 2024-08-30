package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.dto.AddCartDTO;
import org.squad9.vehiclerentalservice.dto.BookingDetailsDTO;
import org.squad9.vehiclerentalservice.model.ShoppingCartModel;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.DriverModel;
import org.squad9.vehiclerentalservice.service.ShoppingCartServiceImpl;
import org.squad9.vehiclerentalservice.service.CarServiceImpl;
import org.squad9.vehiclerentalservice.service.DriverServiceImpl;

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
    public ResponseEntity<List<ShoppingCartModel>> findAll(){
        List<ShoppingCartModel> shoppingCarts = shoppingCartService.findAll();
        return ResponseEntity.ok(shoppingCarts);
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<ShoppingCartModel> findByDriver(@PathVariable String email){
        DriverModel driver = driverService.findByEmail(email);
        ShoppingCartModel shoppingCart = shoppingCartService.findByMotorista(driver);

        return ResponseEntity.ok(shoppingCart);
    }

    @PostMapping(value = "/adicionar-ao-carrinho")
    public ResponseEntity<ShoppingCartModel> addToCart(@RequestBody AddCartDTO addCartDTO){
        DriverModel driver = driverService.findByEmail(addCartDTO.getEmail());
        ShoppingCartModel shoppingCart = shoppingCartService.findByMotorista(driver);
        CarModel carro = carService.findById(addCartDTO.getId());

        ShoppingCartModel response = shoppingCartService.addCarros(shoppingCart, carro);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/detalhes-da-reserva")
    public ResponseEntity<BookingDetailsDTO> getBookingDetails(@RequestBody BookingDetailsDTO bookingDetailsDTO) {
        String email = bookingDetailsDTO.getEmailMotorista();
        DriverModel driver = driverService.findByEmail(email);

        bookingDetailsDTO.setMotorista(driver);

        return ResponseEntity.ok(bookingDetailsDTO);
    }

    @GetMapping(value = "/carrinho/{carrinhoId}")
    public ResponseEntity<List<CarModel>> findCarsInCart(@PathVariable UUID carrinhoId){
        List<CarModel> carrosNoCarrinho = shoppingCartService.getCarrosByCarrinhoId(carrinhoId);
        return ResponseEntity.ok(carrosNoCarrinho);
    }

    @GetMapping(value = "/carrinho/{carrinhoId}/{carroId}")
    public ResponseEntity<?> findByCarroId(@PathVariable UUID carrinhoId, @PathVariable UUID carroId){
        try {
            CarModel carro = carService.findById(carroId);
            ShoppingCartModel shoppingCart = shoppingCartService.findById(carrinhoId);

            return ResponseEntity.ok(shoppingCartService.findByCarroId(shoppingCart, carro));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar carros no carrinho: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/carrinho/{carrinhoId}/{carroId}")
    public ResponseEntity<String> removerCarroDoCarrinho(@PathVariable UUID carrinhoId, @PathVariable UUID carroId) {
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
