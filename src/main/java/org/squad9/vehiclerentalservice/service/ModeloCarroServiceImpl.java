package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.squad9.vehiclerentalservice.model.ModeloCarroModel;
import org.squad9.vehiclerentalservice.model.util.Categoria;
import org.squad9.vehiclerentalservice.repository.ModeloCarroRepository;
import org.squad9.vehiclerentalservice.service.interfaces.ModeloCarroService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ModeloCarroServiceImpl implements ModeloCarroService {
    private ModeloCarroRepository modeloCarroRepository;

    @Override
    public ModeloCarroModel save(ModeloCarroModel modeloCarro) {
        try {
            return modeloCarroRepository.save(modeloCarro);
        }catch (Exception e){
            System.out.println("Não foi possível salvar modelo de carro!");
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<ModeloCarroModel> findAll() {
        try {
            return modeloCarroRepository.findAll();
        }catch (Exception e){
            System.out.println("Não foi possível encontrar registros de modelos de carro!");
        }
        return null;
    }

    public List<ModeloCarroModel> findByCategoria(@PathVariable Categoria categoria){
        try{
            return modeloCarroRepository.findByCategoria(categoria);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public ModeloCarroModel findById(UUID id) {
        try{
            Optional<ModeloCarroModel> modeloCarroOptional = modeloCarroRepository.findById(id);
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
            modeloCarroRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover modelo de carro: " + e.getMessage());
        }
    }
}
