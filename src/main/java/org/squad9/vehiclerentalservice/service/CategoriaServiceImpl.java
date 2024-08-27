package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.model.util.Categoria;
import org.squad9.vehiclerentalservice.service.interfaces.CategoriaService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {
    @Override
    public List<String> getCategorias(){
        return Stream.of(Categoria.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
