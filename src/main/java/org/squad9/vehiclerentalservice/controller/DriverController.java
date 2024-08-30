package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.model.DriverModel;
import org.squad9.vehiclerentalservice.service.DriverServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/motoristas")
@RequiredArgsConstructor
public class DriverController {
    private final DriverServiceImpl driverService;

    @GetMapping
    ResponseEntity<List<DriverModel>> findAll() {
        List<DriverModel> drivers = driverService.findAll();
        return ResponseEntity.ok(drivers);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<DriverModel> findById(@PathVariable UUID id) {
        DriverModel driver = driverService.findById(id);
        return ResponseEntity.ok(driver);
    }

    @GetMapping(value = "/{email}")
    ResponseEntity<DriverModel> findByEmail(@PathVariable String email) {
        DriverModel driver = driverService.findByEmail(email);
        return ResponseEntity.ok(driver);
    }

    @PostMapping
    ResponseEntity<DriverModel> create(@RequestBody DriverModel driver) {
        //TODO: implement this
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id) {
        driverService.remove(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<DriverModel> update(@PathVariable UUID id, @RequestBody DriverModel driver) {
        DriverModel driverModel = driverService.update(id, driver);
        return ResponseEntity.ok(driverModel);
    }
}
