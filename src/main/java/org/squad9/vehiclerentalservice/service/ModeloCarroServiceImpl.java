package org.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.system.entity.Carro;
import org.system.entity.Categoria;
import org.system.entity.Fabricante;
import org.system.entity.ModeloCarro;
import org.system.repository.ModeloCarroRepository;
import org.system.service.interfaces.ModeloCarroService;

import java.util.List;
import java.util.Optional;

@Service
public class ModeloCarroServiceImpl implements ModeloCarroService {
    @Autowired
    private ModeloCarroRepository modeloCarroRepository;
    @Override
    public ModeloCarro save(ModeloCarro modeloCarro) {
        try {
            return modeloCarroRepository.save(modeloCarro);
        }catch (Exception e){
            System.out.println("Não foi possível salvar modelo de carro!");
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<ModeloCarro> findAll() {
        try {
            return modeloCarroRepository.findAll();
        }catch (Exception e){
            System.out.println("Não foi possível encontrar registros de modelos de carro!");
        }
        return null;
    }

    public List<ModeloCarro> findByCategoria(@PathVariable Categoria categoria){
        try{
            return modeloCarroRepository.findByCategoria(categoria);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public ModeloCarro findById(Long id) {
        try{
            Optional<ModeloCarro> modeloCarroOptional = modeloCarroRepository.findById(id);
            if (modeloCarroOptional.isPresent()){
                ModeloCarro modeloCarro = modeloCarroOptional.get();
                return modeloCarro;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    @Override
    public void remove(Long id){
        try {
            modeloCarroRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover modelo de carro: " + e.getMessage());
        }
    }
}
