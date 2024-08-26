package org.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.system.entity.CarrinhoCompra;
import org.system.entity.Carro;
import org.system.entity.Motorista;
import org.system.repository.CarrinhoCompraRepository;
import org.system.service.interfaces.CarrinhoCompraService;

import java.util.*;

@Service
public class CarrinhoCompraServiceImpl implements CarrinhoCompraService {
    @Autowired
    private CarrinhoCompraRepository carrinhoCompraRepository;
    @Autowired
    private CarroServiceImpl carroService;
    @Override
    public List<CarrinhoCompra> findAll() {
        List<CarrinhoCompra> carrinho = carrinhoCompraRepository.findAll();
        if (carrinho.isEmpty()){return null;}
        return carrinho;
    }

    @Override
    public CarrinhoCompra save(CarrinhoCompra carrinhoCompra){
        try{
            return carrinhoCompraRepository.save(carrinhoCompra);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void addCarros(CarrinhoCompra carrinhoCompra, Carro carro){
        try{
            List<Carro> listaCarros = carrinhoCompra.getListaCarros();
            listaCarros.add(carro);
            carrinhoCompra.setListaCarros(listaCarros);

            carrinhoCompraRepository.save(carrinhoCompra);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public CarrinhoCompra findByMotorista(Motorista motorista) {
        try{
            return carrinhoCompraRepository.findByMotorista(motorista);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public CarrinhoCompra findById(Long carrinhoId) {
        try{
            Optional<CarrinhoCompra> carrinhoOptional = carrinhoCompraRepository.findById(carrinhoId);
            if (carrinhoOptional.isPresent()){
                CarrinhoCompra carrinhoCompra = carrinhoOptional.get();
                return carrinhoCompra;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    public Carro findByCarroId(CarrinhoCompra carrinhoCompra, Carro carro){
        Long carroId = carro.getId();

        List<Carro> listaCarros = carrinhoCompra.getListaCarros();
        for (Carro carroCarrinho : listaCarros) {
            if (carroCarrinho.getId().equals(carroId)) {
                return carroCarrinho;
            }
        }

        throw new NoSuchElementException("Carro não encontrado no carrinho");
    }

    @Override
    public void removerCarro(CarrinhoCompra carrinhoCompra, Carro carro) {
        List<Carro> listaCarros = carrinhoCompra.getListaCarros();
        Iterator<Carro> iterator = listaCarros.iterator();

        while (iterator.hasNext()) {
            Carro carroCarrinho = iterator.next();
            if (carroCarrinho.getId().equals(carro.getId())) {
                iterator.remove();
                break;
            }
        }

        save(carrinhoCompra);
    }

    public List<Carro> getCarrosByCarrinhoId(Long carrinhoId) {
        CarrinhoCompra carrinho = carrinhoCompraRepository.findById(carrinhoId)
                .orElse(null);

        if (carrinho == null) {
            return Collections.emptyList();
        }

        return carrinho.getListaCarros();
    }

    public void removeCarro(CarrinhoCompra carrinhoCompra, Carro carro){

    }
}
