package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.ApoliceSeguroModel;

import java.util.List;
import java.util.UUID;

public interface ApolicesService {
    ApoliceSeguroModel findById(UUID id);
    ApoliceSeguroModel save(ApoliceSeguroModel apoliceSeguro);
    List<ApoliceSeguroModel> findAll();
    void remove(UUID id);
}
