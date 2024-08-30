package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.model.ShoppingCartModel;
import org.squad9.vehiclerentalservice.service.ShoppingCartServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/carrinhos-compras")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartServiceImpl shoppingCartService;

    @GetMapping
    ResponseEntity<List<ShoppingCartModel>> findAll(){
        List<ShoppingCartModel> shoppingCarts = shoppingCartService.findAll();
        return ResponseEntity.ok(shoppingCarts);
    }

    @GetMapping("/{id}")
    ResponseEntity<ShoppingCartModel> findById(@PathVariable UUID id){
        ShoppingCartModel shoppingCartModel = shoppingCartService.findById(id);
        return ResponseEntity.ok(shoppingCartModel);
    }

    @GetMapping("/motorista/{email}")
    ResponseEntity<ShoppingCartModel> findByDriver(@PathVariable String email) {
        ShoppingCartModel shoppingCartModel = shoppingCartService.findByDriver(email);
        return ResponseEntity.ok(shoppingCartModel);
    }

    @PutMapping("/{id}")
    ResponseEntity<ShoppingCartModel> update(@PathVariable UUID id, @RequestBody ShoppingCartModel shoppingCart) {
        ShoppingCartModel shoppingCartModel = shoppingCartService.update(id, shoppingCart);
        return ResponseEntity.ok(shoppingCartModel);
    }
}
