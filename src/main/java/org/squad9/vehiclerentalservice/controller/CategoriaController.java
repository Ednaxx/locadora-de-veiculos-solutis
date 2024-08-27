package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.squad9.vehiclerentalservice.service.CategoriaServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private CategoriaServiceImpl categoriaServiceImpl;
    @GetMapping
    public ResponseEntity<List<String>> getCategorias(){
        return ResponseEntity.ok(categoriaServiceImpl.getCategorias());
    }
}
