package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.model.CarrinhoCompraModel;
import org.squad9.vehiclerentalservice.model.CarroModel;
import org.squad9.vehiclerentalservice.model.MotoristaModel;
import org.squad9.vehiclerentalservice.repository.CarrinhoCompraRepository;
import org.squad9.vehiclerentalservice.service.interfaces.CarrinhoCompraService;

import java.util.*;

@Service
@AllArgsConstructor
public class CarrinhoCompraServiceImpl implements CarrinhoCompraService {
    private CarrinhoCompraRepository carrinhoCompraRepository;

    @Override
    public List<CarrinhoCompraModel> findAll() {
        List<CarrinhoCompraModel> carrinho = carrinhoCompraRepository.findAll();
        if (carrinho.isEmpty()) return null;

        return carrinho;
    }

    @Override
    public CarrinhoCompraModel save(CarrinhoCompraModel carrinhoCompra) {
        try{
            return carrinhoCompraRepository.save(carrinhoCompra);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void addCarros(CarrinhoCompraModel carrinhoCompra, CarroModel carro) {
        try{
            List<CarroModel> listaCarros = carrinhoCompra.getListaCarros();
            listaCarros.add(carro);
            carrinhoCompra.setListaCarros(listaCarros);

            carrinhoCompraRepository.save(carrinhoCompra);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public CarrinhoCompraModel findByMotorista(MotoristaModel motorista) {
        try{
            return carrinhoCompraRepository.findByMotorista(motorista);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public CarrinhoCompraModel findById(UUID carrinhoId) {
        try{
            Optional<CarrinhoCompraModel> carrinhoOptional = carrinhoCompraRepository.findById(carrinhoId);
            if (carrinhoOptional.isPresent()){
                return carrinhoOptional.get();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    public CarroModel findByCarroId(CarrinhoCompraModel carrinhoCompra, CarroModel carro){
        UUID carroId = carro.getId();

        List<CarroModel> listaCarros = carrinhoCompra.getListaCarros();
        for (CarroModel carroCarrinho : listaCarros) {
            if (carroCarrinho.getId().equals(carroId)) {
                return carroCarrinho;
            }
        }

        throw new NoSuchElementException("Carro não encontrado no carrinho");
    }

    @Override
    public void removerCarro(CarrinhoCompraModel carrinhoCompra, CarroModel carro) {
        List<CarroModel> listaCarros = carrinhoCompra.getListaCarros();
        Iterator<CarroModel> iterator = listaCarros.iterator();

        while (iterator.hasNext()) {
            CarroModel carroCarrinho = iterator.next();
            if (carroCarrinho.getId().equals(carro.getId())) {
                iterator.remove();
                break;
            }
        }

        save(carrinhoCompra);
    }

    public List<CarroModel> getCarrosByCarrinhoId(UUID carrinhoId) {
        CarrinhoCompraModel carrinho = carrinhoCompraRepository.findById(carrinhoId)
                .orElse(null);

        if (carrinho == null) {
            return Collections.emptyList();
        }

        return carrinho.getListaCarros();
    }

    public void removeCarro(CarrinhoCompraModel carrinhoCompra, CarroModel carro) {
        // TODO: implement this
    }
}
