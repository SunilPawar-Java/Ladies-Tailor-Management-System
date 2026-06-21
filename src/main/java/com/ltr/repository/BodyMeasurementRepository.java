package com.ltr.repository;

import com.ltr.model.BodyMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyMeasurementRepository extends JpaRepository<BodyMeasurement, Long> {
}
