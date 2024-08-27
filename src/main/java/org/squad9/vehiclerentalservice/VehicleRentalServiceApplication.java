package org.squad9.vehiclerentalservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "org.squad9.vehiclerentalservice.repository")
public class VehicleRentalServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleRentalServiceApplication.class, args);
    }

}
