package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.squad9.vehiclerentalservice.model.CarModelModel;
import org.squad9.vehiclerentalservice.model.util.Category;
import org.squad9.vehiclerentalservice.repository.CarModelRepository;
import org.squad9.vehiclerentalservice.service.interfaces.CarModelService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarModelServiceImpl implements CarModelService {
    private CarModelRepository carModelRepository;

    @Override
    public CarModelModel save(CarModelModel modeloCarro) {
        try {
            return carModelRepository.save(modeloCarro);
        }catch (Exception e){
            System.out.println("Não foi possível salvar modelo de car!");
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<CarModelModel> findAll() {
        try {
            return carModelRepository.findAll();
        }catch (Exception e){
            System.out.println("Não foi possível encontrar registros de modelos de car!");
        }
        return null;
    }

    // TODO: Isso eh para estar no service de car
    public List<CarModelModel> findByCategoria(@PathVariable Category category){
        try{
            return carModelRepository.findByCategoria(category);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public CarModelModel findById(UUID id) {
        try{
            Optional<CarModelModel> modeloCarroOptional = carModelRepository.findById(id);
            if (modeloCarroOptional.isPresent()){
                return modeloCarroOptional.get();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    @Override
    public void remove(UUID id){
        try {
            carModelRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover modelo de car: " + e.getMessage());
        }
    }
}
