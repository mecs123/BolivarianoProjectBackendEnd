package com.bolivariano.period.service.impl;

import com.bolivariano.period.entities.Period;
import com.bolivariano.period.repository.PeriodRepository;
import com.bolivariano.period.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    private PeriodRepository periodRepository;

    @Override
    public List<Period> findAllPeriods() {
        return periodRepository.findAll();
    }

    @Override
    public Optional<Period> findPeriodById(Long id) {
        return periodRepository.findById(id);
    }

    @Override
    public Period savePeriod(Period period) {
        return periodRepository.save(period);
    }

    @Override
    public Optional<Period> updatePeriod(Long id, Period period) {
        return periodRepository.findById(id).map(existingPeriod -> {
            existingPeriod.setName(period.getName());
            return periodRepository.save(existingPeriod);
        });
    }

    @Override
    public boolean deletePeriodById(Long id) {
        if (periodRepository.existsById(id)) {
            periodRepository.deleteById(id);
            return true;
        }
        return false;
    }
}