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

    private ManufacturerService manufacturerService;

    @GetMapping
    public ResponseEntity<List<ManufacturerModel>> findAll(){
        List<ManufacturerModel> manufacturers = manufacturerService.findAll();
        return ResponseEntity.ok(manufacturers);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ManufacturerModel> findById(@PathVariable UUID id) {
        ManufacturerModel manufacturer = manufacturerService.findById(id);
        return ResponseEntity.ok(manufacturer);
    }

    @PostMapping
    public ResponseEntity<ManufacturerModel> create(@RequestBody ManufacturerModel manufacturer){
        ManufacturerModel newManufacturer = manufacturerService.save(manufacturer);
        return ResponseEntity.status(HttpStatus.CREATED).body(newManufacturer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        manufacturerService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
