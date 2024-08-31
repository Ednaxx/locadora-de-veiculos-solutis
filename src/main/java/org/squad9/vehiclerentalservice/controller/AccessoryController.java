package org.squad9.vehiclerentalservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.dto.request.AccessoryRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.AccessoryResponseDTO;
import org.squad9.vehiclerentalservice.service.AccessoryServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/acessorios")
@RequiredArgsConstructor
public class AccessoryController {
    private final AccessoryServiceImpl accessoryService;

    @GetMapping
    ResponseEntity<List<AccessoryResponseDTO>> findAll(){
        return ResponseEntity.ok(accessoryService.findAll());
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<AccessoryResponseDTO> findById(@PathVariable UUID id){
        return ResponseEntity.ok(accessoryService.findById(id));
    }

    @PostMapping
    ResponseEntity<AccessoryResponseDTO> create(@RequestBody @Valid AccessoryRequestDTO accessory) {
        AccessoryResponseDTO response = accessoryService.save(accessory);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id){
        accessoryService.remove(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<AccessoryResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid AccessoryRequestDTO accessory){
        AccessoryResponseDTO response = accessoryService.update(id, accessory);
        return ResponseEntity.ok(response);
    }
}
