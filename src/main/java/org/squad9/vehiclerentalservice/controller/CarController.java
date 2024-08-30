package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.dto.request.CarRequestDTO;
import org.squad9.vehiclerentalservice.dto.request.DateIntervalRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.CarResponseDTO;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.util.Category;
import org.squad9.vehiclerentalservice.service.CarServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/carros")
@RequiredArgsConstructor
public class CarController {
    private final CarServiceImpl carService;

    @GetMapping
    ResponseEntity<List<CarResponseDTO>> findAll() {
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<CarResponseDTO> findById(@PathVariable UUID id) {
        CarModel car = carService.findById(id);
        return ResponseEntity.ok(car);
    }

    @GetMapping(value = "/disponiveis")
    ResponseEntity<List<CarResponseDTO>> findAvailableOnDate(@RequestBody DateIntervalRequestDTO request) {
        List<CarResponseDTO> availableCars = carService.findAvailableOnDate(request);
        return ResponseEntity.ok(availableCars);
    }

    @GetMapping(value = "/categoria/{categoria}")
    ResponseEntity<List<CarResponseDTO>> findByCategory(@PathVariable Category categoria) {
        List<CarResponseDTO> cars = carService.findByCategory(categoria);
        return ResponseEntity.ok(cars);
    }

    @GetMapping(value = "/modelo/{modelo_id}")
    ResponseEntity<List<CarResponseDTO>> findByCarModel(@PathVariable UUID modelo_id) {
        List<CarResponseDTO> cars = carService.findByCarModel(modelo_id);
        return ResponseEntity.ok(cars);
    }

    @GetMapping(value = "/acessorio/{acessorio_id}")
    ResponseEntity<List<CarResponseDTO>> findByAccessories(@PathVariable UUID acessorio_id) {
        List<CarResponseDTO> response = carService.findByAcessorio(acessorio_id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    ResponseEntity<CarResponseDTO> create(@RequestBody CarRequestDTO request) {
        CarResponseDTO response = carService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id){
        carService.remove(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<CarResponseDTO> update(@PathVariable UUID id, @RequestBody CarRequestDTO request) {
        CarResponseDTO response = carService.update(id, request);
        return ResponseEntity.ok(response);
    }
}
