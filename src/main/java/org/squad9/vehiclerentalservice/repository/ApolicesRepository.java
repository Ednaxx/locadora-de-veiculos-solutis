package org.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.system.entity.ApoliceSeguro;

public interface ApolicesRepository extends JpaRepository<ApoliceSeguro, Long> {
}
