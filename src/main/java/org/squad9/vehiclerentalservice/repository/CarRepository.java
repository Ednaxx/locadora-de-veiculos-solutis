package org.squad9.vehiclerentalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.squad9.vehiclerentalservice.model.AccessoryModel;
import org.squad9.vehiclerentalservice.model.CarModel;
import org.squad9.vehiclerentalservice.model.CarModelModel;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CarRepository extends JpaRepository<CarModel, UUID> {
    boolean existsByLicensePlate(String placa);
    boolean existsByChassi(String chassi);
    List<CarModel> findByCarModelId(UUID modeloCarroId);
    List<CarModel> findByCategoryId(UUID categoryId);
    List<CarModel> findByAccessoriesId(UUID accessoryId);

    @Query("SELECT c FROM CarModel c LEFT JOIN c.occupiedDates d " +
            "WHERE (d IS NULL OR NOT (d BETWEEN :startDate AND :returnDate))")
    List<CarModel> findAvailableOnDate(@Param("startDate") LocalDate startDate,
                                       @Param("returnDate") LocalDate returnDate);

}
