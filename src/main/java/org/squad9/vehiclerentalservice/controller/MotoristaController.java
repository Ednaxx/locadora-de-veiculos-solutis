package org.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.entity.CarrinhoCompra;
import org.system.entity.Motorista;
import org.system.service.CarrinhoCompraServiceImpl;
import org.system.service.MotoristaServiceImpl;
import java.util.List;

@RestController // métodos deste controlador retornarão objetos que serão serializados em JSON e enviados como resposta HTTP.
@RequestMapping("/motoristas")
public class MotoristaController {
    @Autowired
    private MotoristaServiceImpl motoristaService;
    @Autowired
    private CarrinhoCompraServiceImpl carrinhoCompraService;

    @GetMapping
    public List<Motorista> findAll() {
        return motoristaService.findAll();
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Motorista motorista) {
        try {
            CarrinhoCompra carrinhoCompra = new CarrinhoCompra();
            CarrinhoCompra newCarrinhoCompra = carrinhoCompraService.save(carrinhoCompra);

            motorista.setCarrinhoCompra(newCarrinhoCompra);
            Motorista newMotorista = motoristaService.save(motorista);

            carrinhoCompra.setMotorista(newMotorista);
            carrinhoCompraService.save(carrinhoCompra);

            return ResponseEntity.ok("Motorista cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar motorista: " + e.getMessage());
        }
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        Motorista motorista = motoristaService.findByEmail(email);

        return motorista == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email não encontrado!") : ResponseEntity.ok(motorista);
    }

    @DeleteMapping("/{motoristaId}")
    public ResponseEntity<String> removeMotorista(@PathVariable Long motoristaId) {
        try {
            motoristaService.remove(motoristaId);
            return ResponseEntity.ok("Motorista removido com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao remover motorista: " + e.getMessage());
        }
    }
}
