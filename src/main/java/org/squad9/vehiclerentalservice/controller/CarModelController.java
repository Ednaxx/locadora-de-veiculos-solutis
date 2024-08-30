package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.model.CarModelModel;
import org.squad9.vehiclerentalservice.model.util.Category;
import org.squad9.vehiclerentalservice.service.CarModelServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/modelos-de-carro")
@RequiredArgsConstructor
public class CarModelController {
    private final CarModelServiceImpl carModelService;

    @GetMapping
    ResponseEntity<List<CarModelModel>> findAll(){
        List<CarModelModel> carModels = carModelService.findAll();
        return ResponseEntity.ok(carModels);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<CarModelModel> findById(@PathVariable UUID id) {
        CarModelModel carModel = carModelService.findById(id);
        return ResponseEntity.ok(carModel);
    }

    @GetMapping(value = "/categoria/{categoria}")
    ResponseEntity<List<CarModelModel>> findByCategory(@PathVariable Category categoria){
        List<CarModelModel> carModels = carModelService.findByCategoria(categoria);
        return ResponseEntity.ok(carModels);
    }

    @PostMapping
    ResponseEntity<CarModelModel> create(@RequestBody CarModelModel carModel){
        CarModelModel newModeloCarro = carModelService.save(carModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(newModeloCarro);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id){
        carModelService.remove(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<CarModelModel> update(@PathVariable UUID id, @RequestBody CarModelModel model) {
        CarModelModel carModelModel = carModelService.update(id, model);
        return ResponseEntity.ok(carModelModel);
    }
}
