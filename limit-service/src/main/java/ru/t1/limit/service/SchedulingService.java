package ru.t1.limit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author YKozlova
 */
@Component
@RequiredArgsConstructor
public class SchedulingService {

    public static final String DAYLY_AT_MIDNIGHT_CRON = "0 0 0 * * *";

    private final LimitService limitService;

    @Scheduled(cron = DAYLY_AT_MIDNIGHT_CRON)
    public void setDefaultLimit() {
        limitService.updateAllLimits();
    }
}
