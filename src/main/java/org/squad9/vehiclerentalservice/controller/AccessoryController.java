package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.model.AccessoryModel;
import org.squad9.vehiclerentalservice.service.AccessoryServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/acessorios")
@RequiredArgsConstructor
public class AccessoryController {
    private AccessoryServiceImpl accessoryService;

    @GetMapping
    public ResponseEntity<List<AccessoryModel>> findAll(){
        return ResponseEntity.ok(accessoryService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccessoryModel> findById(@PathVariable UUID id){
        return ResponseEntity.ok(accessoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AccessoryModel> create(@RequestBody AccessoryModel accessory) {
        AccessoryModel newAccessory = accessoryService.save(accessory);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccessory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        accessoryService.remove(id);
        return ResponseEntity.ok().build();
    }
}