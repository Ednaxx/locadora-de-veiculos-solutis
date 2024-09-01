package org.squad9.vehiclerentalservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.dto.request.CarTypeRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.CarTypeResponseDTO;
import org.squad9.vehiclerentalservice.enums.Category;
import org.squad9.vehiclerentalservice.service.CarTypeServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/modelos-de-carro")
@RequiredArgsConstructor
public class CarTypeController {
    private final CarTypeServiceImpl carModelService;

    @GetMapping
    ResponseEntity<List<CarTypeResponseDTO>> findAll(){
        List<CarTypeResponseDTO> response = carModelService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<CarTypeResponseDTO> findById(@PathVariable UUID id) {
        CarTypeResponseDTO response = carModelService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/categoria/{categoria}")
    ResponseEntity<List<CarTypeResponseDTO>> findByCategory(@PathVariable Category categoria){
        List<CarTypeResponseDTO> response = carModelService.findByCategoria(categoria);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    ResponseEntity<CarTypeResponseDTO> create(@RequestBody @Valid CarTypeRequestDTO request){
        CarTypeResponseDTO response = carModelService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id){
        carModelService.remove(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<CarTypeResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid CarTypeRequestDTO request) {
        CarTypeResponseDTO response = carModelService.update(id, request);
        return ResponseEntity.ok(response);
    }
}
