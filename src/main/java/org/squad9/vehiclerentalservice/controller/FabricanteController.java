package org.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.entity.Carro;
import org.system.entity.Fabricante;
import org.system.service.interfaces.FabricanteService;

import java.util.List;

@RestController
@RequestMapping("/fabricantes")
public class FabricanteController {
    @Autowired
    private FabricanteService fabricanteService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        try {
            List<Fabricante> fabricantes = fabricanteService.findAll();
            return ResponseEntity.ok(fabricantes);
        }catch (Exception e){
            String errorMessage = "Não foi possível encontrar registros de fabricantes!";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Fabricante> findById(@PathVariable Long id) {
        Fabricante fabricante = fabricanteService.findById(id);

        if (fabricante != null) {
            return ResponseEntity.ok(fabricante);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Fabricante fabricante){
        Fabricante newFabricante = fabricanteService.save(fabricante);
        return ResponseEntity.ok("Fabricante cadastrado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
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
