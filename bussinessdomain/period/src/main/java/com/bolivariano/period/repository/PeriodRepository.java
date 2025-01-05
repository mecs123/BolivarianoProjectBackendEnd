package com.bolivariano.period.repository;

import com.bolivariano.period.entities.Period;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodRepository extends JpaRepository<Period, Long> {
}