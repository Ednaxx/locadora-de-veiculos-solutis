package org.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.entity.Carro;
import org.system.entity.Categoria;
import org.system.entity.Acessorio;
import org.system.entity.ModeloCarro;
import org.system.service.CarroServiceImpl;
import org.system.service.ModeloCarroServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroServiceImpl carroService;
    @Autowired
    private ModeloCarroServiceImpl modeloCarroService;

    @GetMapping
    public List<Carro> findAll() {
        return carroService.findAll();
    }

    //Após a adição das datas
    @GetMapping(value = "/disponiveis")
    public ResponseEntity<List<Carro>> listarCarrosDisponiveis(@RequestParam String dataInicio, @RequestParam String dataDevolucao) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicioParsed = LocalDate.parse(dataInicio, dateFormatter);
        LocalDate dataDevolucaoParsed = LocalDate.parse(dataDevolucao, dateFormatter);

        List<Carro> carrosDisponiveis = carroService.listarCarrosDisponiveis(dataInicioParsed, dataDevolucaoParsed);

        return carrosDisponiveis.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carrosDisponiveis);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Carro> findById(@PathVariable Long id) {
        Carro carro = carroService.findById(id);

        if (carro == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(carro);
    }

    @GetMapping(value = "/categoria/{categoria}")
    public ResponseEntity<List<Carro>> findByCategoria(@PathVariable Categoria categoria) {
        List<ModeloCarro> modelosCategoria = modeloCarroService.findByCategoria(categoria);

        List<Carro> listaCarros = new ArrayList<>();

        for (ModeloCarro modeloCarro : modelosCategoria) {
            List<Carro> carrosDoModelo = carroService.findByModeloCarro(modeloCarro);
            listaCarros.addAll(carrosDoModelo);
        }

        if (listaCarros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listaCarros);
    }

    @GetMapping(value = "/modeloCarro/{modeloCarro}")
    public ResponseEntity<List<Carro>> findByModeloCarro(@PathVariable ModeloCarro modeloCarro) {
        List<Carro> carros = carroService.findByModeloCarro(modeloCarro);

        if (carros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(carros);
    }

    @GetMapping(value = "/acessorio/{acessorio}")
    public ResponseEntity<List<Carro>> findByAcessorios(@PathVariable Acessorio acessorio) {
        List<Carro> carros = carroService.findByAcessorio(acessorio);

        if (carros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(carros);
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Carro carro) {
        try {
            carroService.save(carro);
            return ResponseEntity.ok("Carro cadastrado com sucesso!");
        } catch (RuntimeException  e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar carro: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        try{
            carroService.remove(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }
}
