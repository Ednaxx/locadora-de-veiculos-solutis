package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.model.FabricanteModel;
import org.squad9.vehiclerentalservice.service.interfaces.FabricanteService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fabricantes")
@RequiredArgsConstructor
public class FabricanteController {
    private FabricanteService fabricanteService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        try {
            List<FabricanteModel> fabricantes = fabricanteService.findAll();
            return ResponseEntity.ok(fabricantes);
        }catch (Exception e){
            String errorMessage = "Não foi possível encontrar registros de fabricantes!";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FabricanteModel> findById(@PathVariable UUID id) {
        FabricanteModel fabricante = fabricanteService.findById(id);

        if (fabricante != null) {
            return ResponseEntity.ok(fabricante);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody FabricanteModel fabricante){
        FabricanteModel newFabricante = fabricanteService.save(fabricante);
        return ResponseEntity.ok("Fabricante cadastrado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        try{
            fabricanteService.remove(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
