package org.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.system.entity.Aluguel;
import org.system.entity.CarrinhoCompra;
import org.system.entity.Carro;
import org.system.entity.Motorista;
import org.system.repository.AluguelRepository;
import org.system.service.interfaces.AluguelService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AluguelServiceImpl implements AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;
    @Autowired
    private CarroServiceImpl carroService;

    public List<Aluguel> findAll() {
        return aluguelRepository.findAll();
    }

    public Aluguel save(Aluguel aluguel) {
        try {
            Carro carro = aluguel.getCarro();
            carro.bloquearDatas(aluguel.getDataEntrega(), aluguel.getDataDevolucao());

            carroService.saveNewDates(carro);
            return aluguelRepository.save(aluguel);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean processPayment(@RequestParam String cardNumber, @RequestParam String expirationDate, @RequestParam String cvv) {
        boolean paymentSuccessful = verifyPayment(cardNumber, expirationDate, cvv);

        return paymentSuccessful;
    }

    public boolean verifyPayment(String cardNumber, String expirationDate, String cvv){

        if (!cardNumber.matches("\\d{13,16}")){
            System.out.println("fui eu: Cartão");
            return false;
        }
        if (!cvv.matches("\\d{3}")){
            System.out.println("fui eu: cvv");
            return false;
        }
        if (!isExpirationDateValid(expirationDate)){
            System.out.println("fui eu: Data");
            return false;
        }

        return true;
    }

    private boolean isExpirationDateValid(String expirationDate){
        String[] parts = expirationDate.split("/");
        int month = Integer.parseInt(parts[0]);
        int year = Integer.parseInt(parts[1]);

        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear() % 100;

        System.out.println(month + " / " + currentMonth);
        System.out.println(year + " / " + currentYear);

        return ((year > currentYear) || (year == currentYear && month >= currentMonth));
    }

    public List<Aluguel> findAlugueisMotorista(Motorista motorista) {
        try{
            return aluguelRepository.findByMotorista(motorista);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
