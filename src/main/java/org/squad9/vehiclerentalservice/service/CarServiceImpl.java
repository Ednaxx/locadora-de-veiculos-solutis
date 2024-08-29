package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.squad9.vehiclerentalservice.model.AccessoryModel;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.CarModelModel;
import org.squad9.vehiclerentalservice.repository.CarRepository;
import org.squad9.vehiclerentalservice.service.interfaces.CarService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;

    @Override
    public CarModel save(CarModel carro) {
        try {
            if(!isPlacaMercosulValida(carro.getLicensePlate()) && !isPlacaComumValida(carro.getLicensePlate()))
                throw new IllegalArgumentException("Placa do car inválida!");

            if(!isChassiValido(carro.getChassi()))
                throw new IllegalArgumentException("Chassi do car inválido!");

            if (carRepository.existsByLicensePlate(carro.getLicensePlate()))
                throw new IllegalArgumentException("Placa do car já existente no sistema!");

            if (carRepository.existsByChassi(carro.getChassi()))
                throw new IllegalArgumentException("Número de chassi já existente no sistema!");

            return carRepository.save(carro);
        }
        catch (Exception e) {
            throw new RuntimeException( e.getMessage());
        }
    }

    private boolean isChassiValido(String chassi) {
        String chassiPadrao = "^[A-HJ-NPR-Z0-9]{17}$";

        return chassi.toUpperCase().matches(chassiPadrao);
    }

    private boolean isPlacaComumValida(String placa) {
        String placaPadrao = "^[A-Z]{3}-?\\d{4}$";
        return placa.toUpperCase().matches(placaPadrao);
    }

    private boolean isPlacaMercosulValida(String placa) {
        String placaPadrao = "^[A-Z]{3}\\d[A-Z]{2}\\d{2}$";
        return placa.toUpperCase().matches(placaPadrao);
    }

    public void saveNewDates(CarModel carro){
        try{
            carRepository.save(carro);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<CarModel> listarCarrosDisponiveis(LocalDate dataInicio, LocalDate dataDevolucao) {
        List<CarModel> carrosDisponiveis = new ArrayList<>();
        List<CarModel> todosCarros = carRepository.findAll();

        for (CarModel carro : todosCarros) {
            if (carro.isDisponivelParaAluguel(dataInicio, dataDevolucao)) {
                carrosDisponiveis.add(carro);
            }
        }

        return carrosDisponiveis;
    }

    @Override
    public List<CarModel> findAll() {
        try {
            return carRepository.findAll();
        }
        catch (Exception e){
            System.out.println("Não foi possível encontrar registros de carros!");
        }
        return null;
    }

    @Override
    public CarModel findById(@PathVariable UUID id) {
        try {
            Optional<CarModel> carroOptional = carRepository.findById(id);
            if (carroOptional.isPresent()) {
                return carroOptional.get();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    public List<CarModel> findByModeloCarro(CarModelModel modeloCarro){
        return carRepository.findByCarModel(modeloCarro);
    }

    public List<CarModel> findByAcessorio(AccessoryModel acessorio) {
        return carRepository.findByAccessoriesContaining(acessorio);
    }

    @Override
    public void remove(UUID id){
        try {
            carRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover car: " + e.getMessage());
        }
    }
}
