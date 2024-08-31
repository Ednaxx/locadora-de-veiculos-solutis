package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.dto.request.ManufacturerRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.ManufacturerResponseDTO;
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
    ResponseEntity<List<ManufacturerResponseDTO>> findAll(){
        List<ManufacturerResponseDTO> response = manufacturerService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<ManufacturerResponseDTO> findById(@PathVariable UUID id) {
        ManufacturerResponseDTO response = manufacturerService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    ResponseEntity<ManufacturerResponseDTO> create(@RequestBody ManufacturerRequestDTO request){
        ManufacturerResponseDTO response = manufacturerService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id){
        manufacturerService.remove(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<ManufacturerResponseDTO> update(@PathVariable UUID id, @RequestBody ManufacturerRequestDTO request) {
        ManufacturerResponseDTO response = manufacturerService.update(id, request);
        return ResponseEntity.ok(response);
    }
}
