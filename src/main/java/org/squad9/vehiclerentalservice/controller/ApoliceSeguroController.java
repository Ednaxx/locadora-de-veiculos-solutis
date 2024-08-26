package org.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.entity.Acessorio;
import org.system.entity.ApoliceSeguro;
import org.system.entity.Carro;
import org.system.entity.ModeloCarro;
import org.system.service.ApolicesServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/apolices-seguro")
public class ApoliceSeguroController {
    @Autowired
    private ApolicesServiceImpl apolicesService;

    @GetMapping
    public ResponseEntity<List<ApoliceSeguro>> findAll(){
        try {
            return ResponseEntity.ok(apolicesService.findAll());
        }catch (Exception e){
            System.out.println("Não foi possível encontrar apólices de seguro cadastradas!");
        }
        return null;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ApoliceSeguro> findById(@PathVariable Long id) {
        ApoliceSeguro apoliceSeguro = apolicesService.findById(id);

        if (apoliceSeguro == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(apoliceSeguro);
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody ApoliceSeguro apoliceSeguro) {
        try {
            apolicesService.save(apoliceSeguro);
            return ResponseEntity.ok("Apólice de seguro cadastrada com sucesso!");
        } catch (RuntimeException  e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar apólice de seguro: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        try{
            apolicesService.remove(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
