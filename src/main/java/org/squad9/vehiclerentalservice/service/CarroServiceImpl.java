package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.squad9.vehiclerentalservice.model.AcessorioModel;
import org.squad9.vehiclerentalservice.model.CarroModel;
import org.squad9.vehiclerentalservice.model.ModeloCarroModel;
import org.squad9.vehiclerentalservice.repository.CarroRepository;
import org.squad9.vehiclerentalservice.service.interfaces.CarroService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarroServiceImpl implements CarroService {
    private CarroRepository carroRepository;

    @Override
    public CarroModel save(CarroModel carro) {
        try {
            if(!isPlacaMercosulValida(carro.getPlaca()) && !isPlacaComumValida(carro.getPlaca()))
                throw new IllegalArgumentException("Placa do carro inválida!");

            if(!isChassiValido(carro.getChassi()))
                throw new IllegalArgumentException("Chassi do carro inválido!");

            if (carroRepository.existsByPlaca(carro.getPlaca()))
                throw new IllegalArgumentException("Placa do carro já existente no sistema!");

            if (carroRepository.existsByChassi(carro.getChassi()))
                throw new IllegalArgumentException("Número de chassi já existente no sistema!");

            return carroRepository.save(carro);
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

    public void saveNewDates(CarroModel carro){
        try{
            carroRepository.save(carro);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<CarroModel> listarCarrosDisponiveis(LocalDate dataInicio, LocalDate dataDevolucao) {
        List<CarroModel> carrosDisponiveis = new ArrayList<>();
        List<CarroModel> todosCarros = carroRepository.findAll();

        for (CarroModel carro : todosCarros) {
            if (carro.isDisponivelParaAluguel(dataInicio, dataDevolucao)) {
                carrosDisponiveis.add(carro);
            }
        }

        return carrosDisponiveis;
    }

    @Override
    public List<CarroModel> findAll() {
        try {
            return carroRepository.findAll();
        }
        catch (Exception e){
            System.out.println("Não foi possível encontrar registros de carros!");
        }
        return null;
    }

    @Override
    public CarroModel findById(@PathVariable UUID id) {
        try {
            Optional<CarroModel> carroOptional = carroRepository.findById(id);
            if (carroOptional.isPresent()) {
                CarroModel carro = carroOptional.get();
                return carro;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    public List<CarroModel> findByModeloCarro(ModeloCarroModel modeloCarro){
        return carroRepository.findByModeloCarro(modeloCarro);
    }

    public List<CarroModel> findByAcessorio(AcessorioModel acessorio) {
        return carroRepository.findByAcessoriosContaining(acessorio);
    }

    @Override
    public void remove(UUID id){
        try {
            carroRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover carro: " + e.getMessage());
        }
    }
}
