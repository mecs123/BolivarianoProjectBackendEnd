package com.bolivariano.period.service;

import com.bolivariano.period.entities.Period;

import java.util.List;
import java.util.Optional;

public interface PeriodService {
    List<Period> findAllPeriods();
    Optional<Period> findPeriodById(Long id);
    Period savePeriod(Period period);
    Optional<Period> updatePeriod(Long id, Period period);
    boolean deletePeriodById(Long id);
}