package org.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.entity.*;
import org.system.entity.dto.AdicionarCarrinhoDTO;
import org.system.entity.dto.DetalhesReservaDTO;
import org.system.service.*;
import java.util.List;

@RestController
@RequestMapping("/carrinhos-compras")
public class CarrinhoCompraController {

    @Autowired
    private CarrinhoCompraServiceImpl carrinhoCompraService;
    @Autowired
    private MotoristaServiceImpl motoristaService;
    @Autowired
    private CarroServiceImpl carroService;

    //Consertar Método
    @GetMapping
    public ResponseEntity<?> findAll(){
        try {
            List<org.system.entity.CarrinhoCompra> carrinhoCompras = carrinhoCompraService.findAll();
            return ResponseEntity.ok(carrinhoCompras);
        }catch (Exception e){
            String errorMessage = "Não foi possível encontrar registros de carros em seu carrinho!";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    //método que lista os carros disponíveis no carrinho do cliente
    @GetMapping(value = "/{email}")
    public ResponseEntity<?> findByMotorista(@PathVariable String email){
        try {
            org.system.entity.Motorista motorista = motoristaService.findByEmail(email);
            org.system.entity.CarrinhoCompra carrinhoCompra = carrinhoCompraService.findByMotorista(motorista);

            return ResponseEntity.ok(carrinhoCompra.getListaCarros());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar carros no carrinho: " + e.getMessage());
        }
    }

    //Método que pega o email do motorista logado e o id do carro que o cliente quer e adiciona no carrinho
    @PostMapping(value = "/adicionar-carro")
    public ResponseEntity<String> save(@RequestBody AdicionarCarrinhoDTO adicionarCarrinhoDTO){
        try {
            org.system.entity.Motorista motorista = motoristaService.findByEmail(adicionarCarrinhoDTO.getEmail());
            org.system.entity.CarrinhoCompra carrinhoCompra = carrinhoCompraService.findByMotorista(motorista);
            org.system.entity.Carro carro = carroService.findById(adicionarCarrinhoDTO.getId());

            carrinhoCompraService.addCarros(carrinhoCompra, carro);
            return ResponseEntity.ok("Carro adicionado ao carrinho com sucesso");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar carro no carrinho de compras: " + e.getMessage());
        }
    }


    //Na hora da 1ª confirmação, as informações de motorista logado, carro e as datas da reserva são passadas novamente para
    // o sistema. Ao recarregar a página, os detalhes serão mostrados junto as informações do carro para a 2ª confirmação
    @GetMapping(value = "/detalhes-reserva")
    public ResponseEntity<?> listarCarrosDisponiveis(@RequestBody DetalhesReservaDTO detalhesReservaDTO) {
        String email = detalhesReservaDTO.getEmailMotorista();
        org.system.entity.Motorista motorista = motoristaService.findByEmail(email);

        if (motorista == null) {
            return ResponseEntity.notFound().build();
        }

        detalhesReservaDTO.setMotorista(motorista);

        return ResponseEntity.ok(detalhesReservaDTO);
    }

    //Mostrar lista de carros salvos em um determinado carrinho de compras
    @GetMapping(value = "/carrinho/{carrinhoId}")
    public ResponseEntity<?> findByCarros (@PathVariable Long carrinhoId){
        List<org.system.entity.Carro> carrosNoCarrinho = carrinhoCompraService.getCarrosByCarrinhoId(carrinhoId);

        if (carrosNoCarrinho.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(carrosNoCarrinho);
    }

    //Pegar detalhes de um carro em um carrinho específico
    @GetMapping(value = "/carrinho/{carrinhoId}/{carroId}")
    public ResponseEntity<?> findByCarroId(@PathVariable Long carrinhoId, @PathVariable Long carroId){
        try {
            org.system.entity.Carro carro = carroService.findById(carroId);
            org.system.entity.CarrinhoCompra carrinhoCompra = carrinhoCompraService.findById(carrinhoId);

            return ResponseEntity.ok(carrinhoCompraService.findByCarroId(carrinhoCompra, carro));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar carros no carrinho: " + e.getMessage());
        }
    }

    //Remover carro do carrinho de compras
    @DeleteMapping(value = "/carrinho/{carrinhoId}/{carroId}")
    public ResponseEntity<String> removerCarroDoCarrinho(@PathVariable Long carrinhoId, @PathVariable Long carroId) {
        try {
            org.system.entity.CarrinhoCompra carrinhoCompra = carrinhoCompraService.findById(carrinhoId);
            org.system.entity.Carro carro = carroService.findById(carroId);

            carrinhoCompraService.removerCarro(carrinhoCompra, carro);
            return ResponseEntity.ok("Carro removido do carrinho com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao remover carro do carrinho: " + e.getMessage());
        }
    }
}
