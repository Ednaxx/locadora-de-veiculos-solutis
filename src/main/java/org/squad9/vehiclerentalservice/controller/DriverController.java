package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.model.ShoppingCartModel;
import org.squad9.vehiclerentalservice.model.DriverModel;
import org.squad9.vehiclerentalservice.service.ShoppingCartServiceImpl;
import org.squad9.vehiclerentalservice.service.DriverServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/motoristas")
@RequiredArgsConstructor
public class DriverController {
    private DriverServiceImpl driverService;
    private ShoppingCartServiceImpl shoppingCartService;

    @GetMapping
    public ResponseEntity<List<DriverModel>> findAll() {
        List<DriverModel> drivers = driverService.findAll();
        return ResponseEntity.ok(drivers);
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<DriverModel> findByEmail(@PathVariable String email) {
        DriverModel driver = driverService.findByEmail(email);
        return ResponseEntity.ok(driver);
    }

    @PostMapping
    public ResponseEntity<DriverModel> create(@RequestBody DriverModel driver) {
        ShoppingCartModel shoppingCart = new ShoppingCartModel();
        ShoppingCartModel newShoppingCart = shoppingCartService.save(shoppingCart);

        driver.setShoppingCart(newShoppingCart);
        DriverModel newDriver = driverService.save(driver);

        shoppingCart.setDriver(newDriver);
        shoppingCartService.save(shoppingCart);

        return ResponseEntity.status(HttpStatus.CREATED).body(newDriver);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        driverService.remove(id);
        return ResponseEntity.ok().build();
    }
}
