package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.squad9.vehiclerentalservice.enums.Category;
import org.squad9.vehiclerentalservice.model.CarTypeModel;

import java.util.List;
import java.util.UUID;

public interface CarTypeRepository extends JpaRepository<CarTypeModel, UUID> {
    List<CarTypeModel> findByCategory(Category category);
}
