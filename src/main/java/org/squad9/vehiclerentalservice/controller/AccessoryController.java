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
    private final AccessoryServiceImpl accessoryService;

    @GetMapping
    ResponseEntity<List<AccessoryModel>> findAll(){
        return ResponseEntity.ok(accessoryService.findAll());
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<AccessoryModel> findById(@PathVariable UUID id){
        return ResponseEntity.ok(accessoryService.findById(id));
    }

    @PostMapping
    ResponseEntity<AccessoryModel> create(@RequestBody AccessoryModel accessory) {
        AccessoryModel newAccessory = accessoryService.save(accessory);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccessory);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id){
        accessoryService.remove(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<AccessoryModel> update(@PathVariable UUID id, @RequestBody AccessoryModel accessory){
        AccessoryModel accessoryModel = accessoryService.update(id, accessory);
        return ResponseEntity.ok(accessoryModel);
    }
}
