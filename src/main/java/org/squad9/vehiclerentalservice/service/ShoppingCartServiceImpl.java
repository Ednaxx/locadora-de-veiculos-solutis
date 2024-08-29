package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.model.ShoppingCartModel;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.DriverModel;
import org.squad9.vehiclerentalservice.repository.ShoppingCartRepository;
import org.squad9.vehiclerentalservice.service.interfaces.ShoppingCartService;

import java.util.*;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public List<ShoppingCartModel> findAll() {
        List<ShoppingCartModel> carrinho = shoppingCartRepository.findAll();
        if (carrinho.isEmpty()) return null;

        return carrinho;
    }

    @Override
    public ShoppingCartModel save(ShoppingCartModel carrinhoCompra) {
        try{
            return shoppingCartRepository.save(carrinhoCompra);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ShoppingCartModel addCarros(ShoppingCartModel carrinhoCompra, CarModel carro) {
        try{
            List<CarModel> listaCarros = carrinhoCompra.getCarList();
            listaCarros.add(carro);
            carrinhoCompra.setCarList(listaCarros);

            return shoppingCartRepository.save(carrinhoCompra);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ShoppingCartModel findByMotorista(DriverModel motorista) {
        try{
            return shoppingCartRepository.findByDriver(motorista);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ShoppingCartModel findById(UUID carrinhoId) {
        try{
            Optional<ShoppingCartModel> carrinhoOptional = shoppingCartRepository.findById(carrinhoId);
            if (carrinhoOptional.isPresent()){
                return carrinhoOptional.get();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return null;
    }

    public CarModel findByCarroId(ShoppingCartModel carrinhoCompra, CarModel carro){
        UUID carroId = carro.getId();

        List<CarModel> listaCarros = carrinhoCompra.getCarList();
        for (CarModel carroCarrinho : listaCarros) {
            if (carroCarrinho.getId().equals(carroId)) {
                return carroCarrinho;
            }
        }

        throw new NoSuchElementException("Carro não encontrado no carrinho");
    }

    @Override
    public void removerCarro(ShoppingCartModel carrinhoCompra, CarModel carro) {
        List<CarModel> listaCarros = carrinhoCompra.getCarList();
        Iterator<CarModel> iterator = listaCarros.iterator();

        while (iterator.hasNext()) {
            CarModel carroCarrinho = iterator.next();
            if (carroCarrinho.getId().equals(carro.getId())) {
                iterator.remove();
                break;
            }
        }

        save(carrinhoCompra);
    }

    public List<CarModel> getCarrosByCarrinhoId(UUID carrinhoId) {
        ShoppingCartModel carrinho = shoppingCartRepository.findById(carrinhoId)
                .orElse(null);

        if (carrinho == null) {
            return Collections.emptyList();
        }

        return carrinho.getCarList();
    }

    public void removeCarro(ShoppingCartModel carrinhoCompra, CarModel carro) {
        // TODO: implement this
    }
}
