package org.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.system.service.CategoriaServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaServiceImpl categoriaServiceImpl;
    @GetMapping
    public ResponseEntity<List<String>> getCategorias(){
        return ResponseEntity.ok(categoriaServiceImpl.getCategorias());
    }
}
