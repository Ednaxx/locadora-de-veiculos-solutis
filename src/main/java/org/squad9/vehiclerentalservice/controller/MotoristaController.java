package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.model.CarrinhoCompraModel;
import org.squad9.vehiclerentalservice.model.MotoristaModel;
import org.squad9.vehiclerentalservice.service.CarrinhoCompraServiceImpl;
import org.squad9.vehiclerentalservice.service.MotoristaServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/motoristas")
@RequiredArgsConstructor
public class MotoristaController {
    private MotoristaServiceImpl motoristaService;
    private CarrinhoCompraServiceImpl carrinhoCompraService;

    @GetMapping
    public List<MotoristaModel> findAll() {
        return motoristaService.findAll();
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody MotoristaModel motorista) {
        try {
            CarrinhoCompraModel carrinhoCompra = new CarrinhoCompraModel();
            CarrinhoCompraModel newCarrinhoCompra = carrinhoCompraService.save(carrinhoCompra);

            motorista.setShoppingCart(newCarrinhoCompra);
            MotoristaModel newMotorista = motoristaService.save(motorista);

            carrinhoCompra.setDriver(newMotorista);
            carrinhoCompraService.save(carrinhoCompra);

            return ResponseEntity.ok("Motorista cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar motorista: " + e.getMessage());
        }
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        MotoristaModel motorista = motoristaService.findByEmail(email);

        return motorista == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email não encontrado!") : ResponseEntity.ok(motorista);
    }

    @DeleteMapping("/{motoristaId}")
    public ResponseEntity<String> removeMotorista(@PathVariable UUID motoristaId) {
        try {
            motoristaService.remove(motoristaId);
            return ResponseEntity.ok("Motorista removido com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao remover motorista: " + e.getMessage());
        }
    }
}
