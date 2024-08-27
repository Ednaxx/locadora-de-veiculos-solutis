package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad9.vehiclerentalservice.model.AcessorioModel;

import java.util.UUID;

public interface AcessorioRepository extends JpaRepository<AcessorioModel, UUID> {}
