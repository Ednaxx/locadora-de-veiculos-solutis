package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.dto.request.CarModelRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.CarModelResponseDTO;
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
    ResponseEntity<List<CarModelResponseDTO>> findAll(){
        List<CarModelResponseDTO> response = carModelService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<CarModelResponseDTO> findById(@PathVariable UUID id) {
        CarModelResponseDTO response = carModelService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/categoria/{categoria}")
    ResponseEntity<List<CarModelResponseDTO>> findByCategory(@PathVariable Category categoria){
        List<CarModelResponseDTO> response = carModelService.findByCategoria(categoria);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    ResponseEntity<CarModelResponseDTO> create(@RequestBody CarModelRequestDTO request){
        CarModelResponseDTO response = carModelService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id){
        carModelService.remove(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<CarModelResponseDTO> update(@PathVariable UUID id, @RequestBody CarModelRequestDTO request) {
        CarModelResponseDTO response = carModelService.update(id, request);
        return ResponseEntity.ok(response);
    }
}
