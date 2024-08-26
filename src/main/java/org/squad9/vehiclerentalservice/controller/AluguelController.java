package org.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.system.entity.*;
import org.system.service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {

    @Autowired
    private AluguelServiceImpl aluguelService;
    @Autowired
    private CarroServiceImpl carroService;
    @Autowired
    private MotoristaServiceImpl motoristaService;

    @GetMapping(value = "/{email}")
    public ResponseEntity<?> findAlugueisMotorista(@PathVariable String email){
        try {
            org.system.entity.Motorista motorista = motoristaService.findByEmail(email);
            List<org.system.entity.Aluguel> aluguel = aluguelService.findAlugueisMotorista(motorista);

            return ResponseEntity.ok(aluguel);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar carros no carrinho: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<org.system.entity.Aluguel>> findAll() {
        List<org.system.entity.Aluguel> alugueis = aluguelService.findAll();

        if (alugueis.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(alugueis);
    }

    @PostMapping("/pagamento-cartao")
    public String processPayment(@RequestBody Map<String, String> payload) {
        String cardNumber = (payload.get("cardNumber"));
        String expirationDate = payload.get("expirationDate");
        String cvv = payload.get("cvv");

        boolean paymentSuccessful = aluguelService.verifyPayment(cardNumber, expirationDate, cvv);

        return paymentSuccessful ? "redirect:/resumo-reserva" : "redirect:/pagamento-falhou";
    }

    //Após a confirmação de aluguel
    @PostMapping
    public ResponseEntity<String> insert(@RequestBody org.system.entity.Aluguel aluguel) {
        try {
            //Pegar a data atual
            LocalDate dataPedido = LocalDate.now();
            aluguel.setDataPedido(dataPedido);
            System.out.println(aluguel.getDataPedido());

            aluguelService.save(aluguel);
            return ResponseEntity.ok("Aluguel confirmado!");
        } catch (RuntimeException  e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao confirmar aluguel: " + e.getMessage());
        }
    }
}
