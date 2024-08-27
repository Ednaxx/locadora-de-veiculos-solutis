package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.dto.AdicionarCarrinhoDTO;
import org.squad9.vehiclerentalservice.dto.DetalhesReservaDTO;
import org.squad9.vehiclerentalservice.model.CarrinhoCompraModel;
import org.squad9.vehiclerentalservice.model.CarroModel;
import org.squad9.vehiclerentalservice.model.MotoristaModel;
import org.squad9.vehiclerentalservice.service.CarrinhoCompraServiceImpl;
import org.squad9.vehiclerentalservice.service.CarroServiceImpl;
import org.squad9.vehiclerentalservice.service.MotoristaServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/carrinhos-compras")
@RequiredArgsConstructor
public class CarrinhoCompraController {
    private CarrinhoCompraServiceImpl carrinhoCompraService;
    private MotoristaServiceImpl motoristaService;
    private CarroServiceImpl carroService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        try {
            List<CarrinhoCompraModel> carrinhoCompras = carrinhoCompraService.findAll();
            return ResponseEntity.ok(carrinhoCompras);
        }catch (Exception e){
            String errorMessage = "Não foi possível encontrar registros de carros em seu carrinho!";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<?> findByMotorista(@PathVariable String email){
        try {
            MotoristaModel motorista = motoristaService.findByEmail(email);
            CarrinhoCompraModel carrinhoCompra = carrinhoCompraService.findByMotorista(motorista);

            return ResponseEntity.ok(carrinhoCompra.getListaCarros());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar carros no carrinho: " + e.getMessage());
        }
    }

    @PostMapping(value = "/adicionar-carro")
    public ResponseEntity<String> save(@RequestBody AdicionarCarrinhoDTO adicionarCarrinhoDTO){
        try {
            MotoristaModel motorista = motoristaService.findByEmail(adicionarCarrinhoDTO.getEmail());
            CarrinhoCompraModel carrinhoCompra = carrinhoCompraService.findByMotorista(motorista);
            CarroModel carro = carroService.findById(adicionarCarrinhoDTO.getId());

            carrinhoCompraService.addCarros(carrinhoCompra, carro);
            return ResponseEntity.ok("Carro adicionado ao carrinho com sucesso");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar carro no carrinho de compras: " + e.getMessage());
        }
    }

    @GetMapping(value = "/detalhes-reserva")
    public ResponseEntity<?> listarCarrosDisponiveis(@RequestBody DetalhesReservaDTO detalhesReservaDTO) {
        String email = detalhesReservaDTO.getEmailMotorista();
        MotoristaModel motorista = motoristaService.findByEmail(email);

        if (motorista == null) {
            return ResponseEntity.notFound().build();
        }

        detalhesReservaDTO.setMotorista(motorista);

        return ResponseEntity.ok(detalhesReservaDTO);
    }

    //Mostrar lista de carros salvos em um determinado carrinho de compras
    @GetMapping(value = "/carrinho/{carrinhoId}")
    public ResponseEntity<?> findByCarros (@PathVariable UUID carrinhoId){
        List<CarroModel> carrosNoCarrinho = carrinhoCompraService.getCarrosByCarrinhoId(carrinhoId);

        if (carrosNoCarrinho.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(carrosNoCarrinho);
    }

    //Pegar detalhes de um carro em um carrinho específico
    @GetMapping(value = "/carrinho/{carrinhoId}/{carroId}")
    public ResponseEntity<?> findByCarroId(@PathVariable UUID carrinhoId, @PathVariable UUID carroId){
        try {
            CarroModel carro = carroService.findById(carroId);
            CarrinhoCompraModel carrinhoCompra = carrinhoCompraService.findById(carrinhoId);

            return ResponseEntity.ok(carrinhoCompraService.findByCarroId(carrinhoCompra, carro));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar carros no carrinho: " + e.getMessage());
        }
    }

    //Remover carro do carrinho de compras
    @DeleteMapping(value = "/carrinho/{carrinhoId}/{carroId}")
    public ResponseEntity<String> removerCarroDoCarrinho(@PathVariable UUID carrinhoId, @PathVariable UUID carroId) {
        try {
            CarrinhoCompraModel carrinhoCompra = carrinhoCompraService.findById(carrinhoId);
            CarroModel carro = carroService.findById(carroId);

            carrinhoCompraService.removerCarro(carrinhoCompra, carro);
            return ResponseEntity.ok("Carro removido do carrinho com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao remover carro do carrinho: " + e.getMessage());
        }
    }
}
