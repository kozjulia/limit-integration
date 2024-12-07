package ru.t1.limit.service;

import t1.dto.module.dto.LimitDto;

import java.math.BigDecimal;

/**
 * @author YKozlova
 */
public interface LimitService {

    LimitDto create(Long userId);

    boolean update(Long userId, BigDecimal amount);

    void updateAllLimits();

    void deleteByUserId(Long userId);

    LimitDto findByUserId(Long userId);
}
