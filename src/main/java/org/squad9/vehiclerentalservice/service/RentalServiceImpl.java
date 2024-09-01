package org.squad9.vehiclerentalservice.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.squad9.vehiclerentalservice.dto.request.RentalRequestDTO;
import org.squad9.vehiclerentalservice.dto.response.RentalResponseDTO;
import org.squad9.vehiclerentalservice.exception.RestException;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.DriverModel;
import org.squad9.vehiclerentalservice.model.InsurancePolicyModel;
import org.squad9.vehiclerentalservice.model.RentalModel;
import org.squad9.vehiclerentalservice.repository.CarRepository;
import org.squad9.vehiclerentalservice.repository.DriverRepository;
import org.squad9.vehiclerentalservice.repository.InsurancePolicyRepository;
import org.squad9.vehiclerentalservice.repository.RentalRepository;
import org.squad9.vehiclerentalservice.service.interfaces.RentalService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    private final DriverRepository driverRepository;
    private final InsurancePolicyRepository insurancePolicyRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<RentalResponseDTO> findAll() {
        List<RentalModel> rentals = rentalRepository.findAll();
        List<RentalResponseDTO> response = new ArrayList<>();

        rentals.forEach(rental -> response.add(modelMapper.map(rental, RentalResponseDTO.class)));
        return response;
    }

    @Override
    public RentalResponseDTO findById(UUID id) {
        RentalModel rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Aluguel não encontrado com o ID: " + id));

        return modelMapper.map(rental, RentalResponseDTO.class);
    }

    @Override
    public List<RentalResponseDTO> findByDriverEmail(String email) {
        List<RentalModel> rentals = rentalRepository.findByDriverEmail(email);
        List<RentalResponseDTO> response = new ArrayList<>();

        rentals.forEach(rental -> response.add(modelMapper.map(rental, RentalResponseDTO.class)));
        return response;
    }

    @Override
    public RentalResponseDTO save(RentalRequestDTO request) {
        RentalModel rentalToSave = modelMapper.map(request, RentalModel.class);

        CarModel car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Carro não encontrado com o ID: " + request.getCarId()));

        DriverModel driver = driverRepository.findById(request.getDriverId())
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Motorista não encontrado com o ID: " + request.getDriverId()));

        InsurancePolicyModel insurancePolicy = insurancePolicyRepository.findById(request.getInsurancePolicyId())
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Apólice de seguro não encontrada com o ID: " + request.getInsurancePolicyId()));

        if (car.isAvailableForRent(request.getOrderDate(), request.getReturnDate())) {
            car.blockDates(request.getOrderDate(), request.getReturnDate());
        }
        else {
            throw new RestException(HttpStatus.FORBIDDEN, "Carro não disponível para aluguel no período solicitado");
        }

        rentalToSave.setCar(car);
        rentalToSave.setDriver(driver);
        rentalToSave.setInsurancePolicy(insurancePolicy);

        car.getRents().add(rentalToSave);
        driver.getRents().add(rentalToSave);
        insurancePolicy.setRental(rentalToSave);

        carRepository.save(car);
        driverRepository.save(driver);
        insurancePolicyRepository.save(insurancePolicy);

        RentalModel savedRental = rentalRepository.save(rentalToSave);

        return modelMapper.map(savedRental, RentalResponseDTO.class);
    }

    @Override
    public void delete(UUID id) {
        if (!rentalRepository.existsById(id)) {
            throw new RestException(HttpStatus.NOT_FOUND, "Aluguel não encontrado com o ID: " + id);
        }
        rentalRepository.deleteById(id);
    }

    @Override
    public RentalResponseDTO update(UUID id, RentalRequestDTO request) {
        rentalRepository.findById(id).orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "Acessório não encontrado com o ID: " + id));

        RentalModel rentalToUpdate = modelMapper.map(request, RentalModel.class);
        rentalToUpdate.setId(id);
        RentalModel updatedRental = rentalRepository.save(rentalToUpdate);

        return modelMapper.map(updatedRental, RentalResponseDTO.class);
    }
}

