package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad9.vehiclerentalservice.model.CarModelModel;
import org.squad9.vehiclerentalservice.model.util.Category;

import java.util.List;
import java.util.UUID;

public interface CarModelRepository extends JpaRepository<CarModelModel, UUID> {
    List<CarModelModel> findByCategory(Category category);
}
