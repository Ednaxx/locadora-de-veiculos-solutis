package org.system.service;

import org.springframework.stereotype.Service;
import org.system.entity.Categoria;
import org.system.service.interfaces.CategoriaService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Override
    public List<String> getCategorias(){
        return Stream.of(Categoria.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
