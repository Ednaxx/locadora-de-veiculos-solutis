package org.squad9.vehiclerentalservice.service.interfaces;

import org.squad9.vehiclerentalservice.model.AluguelModel;

import java.util.List;

public interface AluguelService {
    public AluguelModel save(AluguelModel aluguel);
    public List<AluguelModel> findAll();
}
