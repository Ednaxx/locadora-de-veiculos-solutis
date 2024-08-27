package org.squad9.vehiclerentalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad9.vehiclerentalservice.model.AluguelModel;
import org.squad9.vehiclerentalservice.model.MotoristaModel;
import org.squad9.vehiclerentalservice.service.AluguelServiceImpl;
import org.squad9.vehiclerentalservice.service.CarroServiceImpl;
import org.squad9.vehiclerentalservice.service.MotoristaServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/alugueis")
@RequiredArgsConstructor
public class AluguelController {

    private AluguelServiceImpl aluguelService;
    private CarroServiceImpl carroService;
    private MotoristaServiceImpl motoristaService;

    @GetMapping(value = "/{email}")
    public ResponseEntity<?> findAlugueisMotorista(@PathVariable String email){
        try {
            MotoristaModel motorista = motoristaService.findByEmail(email);
            List<AluguelModel> aluguel = aluguelService.findAlugueisMotorista(motorista);

            return ResponseEntity.ok(aluguel);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar carros no carrinho: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<AluguelModel>> findAll() {
        List<AluguelModel> alugueis = aluguelService.findAll();

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
    public ResponseEntity<String> insert(@RequestBody AluguelModel aluguel) {
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
