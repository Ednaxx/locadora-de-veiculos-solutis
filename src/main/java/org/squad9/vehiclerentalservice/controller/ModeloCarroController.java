package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.model.ModeloCarroModel;
import org.squad9.vehiclerentalservice.model.util.Categoria;
import org.squad9.vehiclerentalservice.service.ModeloCarroServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/modelos-carro")
@RequiredArgsConstructor
public class ModeloCarroController {
    private ModeloCarroServiceImpl modeloCarroService;

    @GetMapping
    public ResponseEntity<List<ModeloCarroModel>> findAll(){
        try {
            List<ModeloCarroModel> modelosCarro = modeloCarroService.findAll();
            return ResponseEntity.ok(modelosCarro);
        }catch (Exception e){
            System.out.println("Não foi possível encontrar modelos de carro!");
        }
        return null;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ModeloCarroModel> findById(@PathVariable UUID id) {
        ModeloCarroModel modeloCarro = modeloCarroService.findById(id);

        if (modeloCarro != null) {
            return ResponseEntity.ok(modeloCarro);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/categoria/{categoria}")
    public ResponseEntity<List<ModeloCarroModel>> findByCategoria(@PathVariable Categoria categoria){
        try {
            List<ModeloCarroModel> modelosCarro = modeloCarroService.findByCategoria(categoria);
            return ResponseEntity.ok(modelosCarro);
        }catch (Exception e){
            return null;
        }
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody ModeloCarroModel modeloCarro){
        ModeloCarroModel newModeloCarro = modeloCarroService.save(modeloCarro);
        return ResponseEntity.ok("Modelo de carro cadastrado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        try{
            modeloCarroService.remove(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
