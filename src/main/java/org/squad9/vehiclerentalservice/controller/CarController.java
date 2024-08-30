package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.model.AccessoryModel;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.CarModelModel;
import org.squad9.vehiclerentalservice.model.util.Category;
import org.squad9.vehiclerentalservice.service.CarServiceImpl;
import org.squad9.vehiclerentalservice.service.CarModelServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/carros")
@RequiredArgsConstructor
public class CarController {
    private final CarServiceImpl carService;
    private final CarModelServiceImpl carModelService;

    @GetMapping
    public ResponseEntity<List<CarModel>> findAll() {
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping(value = "/disponiveis")
    public ResponseEntity<List<CarModel>> findAllAvailableCars(@RequestParam String startDate, @RequestParam String returnDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate parsedStartDate = LocalDate.parse(startDate, dateFormatter);
        LocalDate parsedReturnDate = LocalDate.parse(returnDate, dateFormatter);

        List<CarModel> availableCars = carService.listarCarrosDisponiveis(parsedStartDate, parsedReturnDate);

        return ResponseEntity.ok(availableCars);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<CarModel> findById(@PathVariable UUID id) {
        CarModel car = carService.findById(id);
        return ResponseEntity.ok(car);
    }

    @GetMapping(value = "/categoria/{categoria}")
    public ResponseEntity<List<CarModel>> findByCategory(@PathVariable Category categoria) {
        // TODO: isso eh para estar no service de car o loop ali eh para estar dentro do metodo, n aqui no controller
        List<CarModelModel> carModels = carModelService.findByCategoria(categoria);
        List<CarModel> cars = new ArrayList<>();

        for (CarModelModel carModel : carModels)
            cars.addAll(carService.findByModeloCarro(carModel));

        return ResponseEntity.ok(cars);
    }

    @GetMapping(value = "/modelo/{modelo}")
    public ResponseEntity<List<CarModel>> findByCarModel(@PathVariable CarModelModel modelo) {
        List<CarModel> cars = carService.findByModeloCarro(modelo);
        return ResponseEntity.ok(cars);
    }

    @GetMapping(value = "/acessorio/{acessorio}")
    public ResponseEntity<List<CarModel>> findByAccessories(@PathVariable AccessoryModel acessorio) {
        List<CarModel> cars = carService.findByAcessorio(acessorio);
        return ResponseEntity.ok(cars);
    }

    @PostMapping
    public ResponseEntity<CarModel> create(@RequestBody CarModel car) {
        CarModel newCar = carService.save(car);
        return ResponseEntity.ok(newCar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        carService.remove(id);
        return ResponseEntity.ok().build();
    }
}
