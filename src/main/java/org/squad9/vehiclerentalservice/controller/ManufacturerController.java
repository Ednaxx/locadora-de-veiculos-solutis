package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.model.ManufacturerModel;
import org.squad9.vehiclerentalservice.service.interfaces.ManufacturerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fabricantes")
@RequiredArgsConstructor
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @GetMapping
    ResponseEntity<List<ManufacturerModel>> findAll(){
        List<ManufacturerModel> manufacturers = manufacturerService.findAll();
        return ResponseEntity.ok(manufacturers);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<ManufacturerModel> findById(@PathVariable UUID id) {
        ManufacturerModel manufacturer = manufacturerService.findById(id);
        return ResponseEntity.ok(manufacturer);
    }

    @PostMapping
    ResponseEntity<ManufacturerModel> create(@RequestBody ManufacturerModel manufacturer){
        ManufacturerModel newManufacturer = manufacturerService.save(manufacturer);
        return ResponseEntity.status(HttpStatus.CREATED).body(newManufacturer);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id){
        manufacturerService.remove(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<ManufacturerModel> update(@PathVariable UUID id, @RequestBody ManufacturerModel manufacturer) {
        ManufacturerModel manufacturerModel = manufacturerService.update(id, manufacturer);
        return ResponseEntity.ok(manufacturerModel);
    }
}
