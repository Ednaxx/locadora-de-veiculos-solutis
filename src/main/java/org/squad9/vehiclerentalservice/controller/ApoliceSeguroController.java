package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.model.ApoliceSeguroModel;
import org.squad9.vehiclerentalservice.service.ApolicesServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/apolices-seguro")
@RequiredArgsConstructor
public class ApoliceSeguroController {
    private ApolicesServiceImpl apolicesService;

    @GetMapping
    public ResponseEntity<List<ApoliceSeguroModel>> findAll(){
        try {
            return ResponseEntity.ok(apolicesService.findAll());
        }catch (Exception e){
            System.out.println("Não foi possível encontrar apólices de seguro cadastradas!");
        }
        return null;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ApoliceSeguroModel> findById(@PathVariable UUID id) {
        ApoliceSeguroModel apoliceSeguro = apolicesService.findById(id);

        if (apoliceSeguro == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(apoliceSeguro);
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody ApoliceSeguroModel apoliceSeguro) {
        try {
            apolicesService.save(apoliceSeguro);
            return ResponseEntity.ok("Apólice de seguro cadastrada com sucesso!");
        } catch (RuntimeException  e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar apólice de seguro: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
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
