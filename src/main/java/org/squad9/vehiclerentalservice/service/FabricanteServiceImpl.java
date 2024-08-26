package org.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.entity.CarrinhoCompra;
import org.system.entity.Fabricante;
import org.system.repository.FabricanteRepository;
import org.system.service.interfaces.FabricanteService;

import java.util.List;
import java.util.Optional;

@Service
public class FabricanteServiceImpl implements FabricanteService {
    @Autowired
    private FabricanteRepository fabricanteRepository;

    @Override
    public Fabricante save(Fabricante fabricante) {
        try {
            return fabricanteRepository.save(fabricante);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Fabricante> findAll() {
        try {
            return fabricanteRepository.findAll();
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Fabricante findById(Long id){
        try{
            Optional<Fabricante> fabricanteOptional = fabricanteRepository.findById(id);
            if (fabricanteOptional.isPresent()){
                Fabricante fabricante = fabricanteOptional.get();
                return fabricante;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }
    @Override
    public void remove(Long id){
        try {
            fabricanteRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover fabricante: " + e.getMessage());
        }
    }
}
