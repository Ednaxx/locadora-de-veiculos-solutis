package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.MotoristaModel;

import java.util.List;
import java.util.UUID;

public interface MotoristaService {
    List<MotoristaModel> findAll();
    MotoristaModel save(MotoristaModel motorista);
    void remove (UUID motorista);
    MotoristaModel findByEmail(String email);
}
