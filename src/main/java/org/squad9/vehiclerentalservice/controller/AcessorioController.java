package org.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.entity.Acessorio;
import org.system.service.AcessorioServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acessorios")
public class AcessorioController {
    @Autowired
    private AcessorioServiceImpl acessorioService;

    @GetMapping
    public ResponseEntity<List<Acessorio>> findAll(){
        return ResponseEntity.ok(acessorioService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Acessorio> findById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(acessorioService.findById(id));
        } catch (RuntimeException  e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Acessorio acessorio) {
        try {
            acessorioService.save(acessorio);
            return ResponseEntity.ok("Acessório cadastrado com sucesso!");
        } catch (RuntimeException  e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar acessório: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
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