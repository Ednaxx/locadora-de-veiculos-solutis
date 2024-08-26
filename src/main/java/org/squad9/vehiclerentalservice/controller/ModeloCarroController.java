package org.system.controller;

import com.mysql.cj.xdevapi.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.entity.Categoria;
import org.system.entity.Fabricante;
import org.system.entity.ModeloCarro;
import org.system.service.ModeloCarroServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/modelos-carro")
public class ModeloCarroController {
    @Autowired
    private ModeloCarroServiceImpl modeloCarroService;

    @GetMapping
    public ResponseEntity<List<ModeloCarro>> findAll(){
        try {
            List<ModeloCarro> modelosCarro = modeloCarroService.findAll();
            return ResponseEntity.ok(modelosCarro);
        }catch (Exception e){
            System.out.println("Não foi possível encontrar modelos de carro!");
        }
        return null;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ModeloCarro> findById(@PathVariable Long id) {
        ModeloCarro modeloCarro = modeloCarroService.findById(id);

        if (modeloCarro != null) {
            return ResponseEntity.ok(modeloCarro);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/categoria/{categoria}")
    public ResponseEntity<List<ModeloCarro>> findByCategoria(@PathVariable Categoria categoria){
        try {
            List<ModeloCarro> modelosCarro = modeloCarroService.findByCategoria(categoria);
            return ResponseEntity.ok(modelosCarro);
        }catch (Exception e){
            return null;
        }
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody ModeloCarro modeloCarro){
        ModeloCarro newModeloCarro = modeloCarroService.save(modeloCarro);
        return ResponseEntity.ok("Modelo de carro cadastrado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
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
