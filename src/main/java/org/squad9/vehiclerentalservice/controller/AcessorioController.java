package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.model.AcessorioModel;
import org.squad9.vehiclerentalservice.service.AcessorioServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/acessorios")
@RequiredArgsConstructor
public class AcessorioController {
    private AcessorioServiceImpl acessorioService;

    @GetMapping
    public ResponseEntity<List<AcessorioModel>> findAll(){
        return ResponseEntity.ok(acessorioService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AcessorioModel> findById(@PathVariable UUID id){
        try {
            return ResponseEntity.ok(acessorioService.findById(id));
        } catch (RuntimeException  e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody AcessorioModel acessorio) {
        try {
            acessorioService.save(acessorio);
            return ResponseEntity.ok("Acessório cadastrado com sucesso!");
        } catch (RuntimeException  e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar acessório: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        try{
            acessorioService.remove(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}