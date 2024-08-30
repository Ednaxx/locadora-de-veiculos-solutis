package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.service.CarServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/carros")
@RequiredArgsConstructor
public class CarController {
    private final CarServiceImpl carService;

    @GetMapping
    ResponseEntity<List<CarModel>> findAll() {
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<CarModel> findById(@PathVariable UUID id) {
        CarModel car = carService.findById(id);
        return ResponseEntity.ok(car);
    }

    @GetMapping(value = "/disponiveis")
    ResponseEntity<List<CarModel>> findAvailableOnDate(@RequestParam String startDate, @RequestParam String returnDate) {
        List<CarModel> availableCars = carService.findAvailableOnDate(startDate, returnDate);
        return ResponseEntity.ok(availableCars);
    }

    @GetMapping(value = "/categoria/{categoria_id}")
    ResponseEntity<List<CarModel>> findByCategory(@PathVariable UUID categoria_id) {
        List<CarModel> cars = carService.findByCategory(categoria_id);
        return ResponseEntity.ok(cars);
    }

    @GetMapping(value = "/modelo/{modelo_id}")
    ResponseEntity<List<CarModel>> findByCarModel(@PathVariable UUID modelo_id) {
        List<CarModel> cars = carService.findByCarModel(modelo_id);
        return ResponseEntity.ok(cars);
    }

    @GetMapping(value = "/acessorio/{acessorio_id}")
    ResponseEntity<List<CarModel>> findByAccessories(@PathVariable UUID acessorio_id) {
        List<CarModel> cars = carService.findByAcessorio(acessorio_id);
        return ResponseEntity.ok(cars);
    }

    @PostMapping
    ResponseEntity<CarModel> create(@RequestBody CarModel car) {
        CarModel newCar = carService.save(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCar);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id){
        carService.remove(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<CarModel> update(@PathVariable UUID id, @RequestBody CarModel car) {
        CarModel carModel = carService.update(id, car);
        return ResponseEntity.ok(carModel);
    }
}
