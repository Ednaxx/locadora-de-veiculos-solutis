package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.model.AcessorioModel;
import org.squad9.vehiclerentalservice.model.CarroModel;
import org.squad9.vehiclerentalservice.model.ModeloCarroModel;
import org.squad9.vehiclerentalservice.model.util.Categoria;
import org.squad9.vehiclerentalservice.service.CarroServiceImpl;
import org.squad9.vehiclerentalservice.service.ModeloCarroServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/carros")
@RequiredArgsConstructor
public class CarroController {
    private CarroServiceImpl carroService;
    private ModeloCarroServiceImpl modeloCarroService;

    @GetMapping
    public List<CarroModel> findAll() {
        return carroService.findAll();
    }

    //Após a adição das datas
    @GetMapping(value = "/disponiveis")
    public ResponseEntity<List<CarroModel>> listarCarrosDisponiveis(@RequestParam String dataInicio, @RequestParam String dataDevolucao) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicioParsed = LocalDate.parse(dataInicio, dateFormatter);
        LocalDate dataDevolucaoParsed = LocalDate.parse(dataDevolucao, dateFormatter);

        List<CarroModel> carrosDisponiveis = carroService.listarCarrosDisponiveis(dataInicioParsed, dataDevolucaoParsed);

        return carrosDisponiveis.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carrosDisponiveis);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<CarroModel> findById(@PathVariable UUID id) {
        CarroModel carro = carroService.findById(id);

        if (carro == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(carro);
    }

    @GetMapping(value = "/categoria/{categoria}")
    public ResponseEntity<List<CarroModel>> findByCategoria(@PathVariable Categoria categoria) {
        List<ModeloCarroModel> modelosCategoria = modeloCarroService.findByCategoria(categoria);

        List<CarroModel> listaCarros = new ArrayList<>();

        for (ModeloCarroModel modeloCarro : modelosCategoria) {
            List<CarroModel> carrosDoModelo = carroService.findByModeloCarro(modeloCarro);
            listaCarros.addAll(carrosDoModelo);
        }

        if (listaCarros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listaCarros);
    }

    @GetMapping(value = "/modeloCarro/{modeloCarro}")
    public ResponseEntity<List<CarroModel>> findByModeloCarro(@PathVariable ModeloCarroModel modeloCarro) {
        List<CarroModel> carros = carroService.findByModeloCarro(modeloCarro);

        if (carros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(carros);
    }

    @GetMapping(value = "/acessorio/{acessorio}")
    public ResponseEntity<List<CarroModel>> findByAcessorios(@PathVariable AcessorioModel acessorio) {
        List<CarroModel> carros = carroService.findByAcessorio(acessorio);

        if (carros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(carros);
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody CarroModel carro) {
        try {
            carroService.save(carro);
            return ResponseEntity.ok("Carro cadastrado com sucesso!");
        } catch (RuntimeException  e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar carro: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
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
