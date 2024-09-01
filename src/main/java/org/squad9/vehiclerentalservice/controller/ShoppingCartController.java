package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.dto.response.CarResponseDTO;
import org.squad9.vehiclerentalservice.dto.response.ShoppingCartResponseDTO;
import org.squad9.vehiclerentalservice.model.util.ApiResponse;
import org.squad9.vehiclerentalservice.service.ShoppingCartServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/carrinhos-compras")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartServiceImpl shoppingCartService;

    @GetMapping
    ResponseEntity<List<ShoppingCartResponseDTO>> findAll(){
        List<ShoppingCartResponseDTO> response = shoppingCartService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    ResponseEntity<ShoppingCartResponseDTO> findById(@PathVariable UUID id){
        ShoppingCartResponseDTO response = shoppingCartService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/motorista/{email}")
    ResponseEntity<ShoppingCartResponseDTO> findByDriver(@PathVariable String email) {
        ShoppingCartResponseDTO shoppingCartModel = shoppingCartService.findByDriver(email);
        return ResponseEntity.ok(shoppingCartModel);
    }

    @GetMapping("/{id}/carros")
    ResponseEntity<List<CarResponseDTO>> findShoppingCartsCars(@PathVariable UUID id){
        List<CarResponseDTO> response = shoppingCartService.findShoppingCartsCars(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/carros/{idCarro}")
    ResponseEntity<List<CarResponseDTO>> addCarToShoppingCart(@PathVariable UUID id, @PathVariable UUID idCarro){
        List<CarResponseDTO> response = shoppingCartService.addCarToShoppingCart(id, idCarro);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/carros/{idCarro}")
    ResponseEntity<List<CarResponseDTO>> removeCarFromShoppingCart(@PathVariable UUID id, @PathVariable UUID idCarro){
        List<CarResponseDTO> response = shoppingCartService.removeCarFromShoppingCart(id, idCarro);
        return ResponseEntity.ok(response);
    }
}
