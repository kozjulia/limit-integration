package ru.t1.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * Клиент для работы с сервисом лимитов
 *
 * @author YKozlova
 */
@FeignClient(name = "limit-client", url = "${integration.limit-service.url}", path = "/limits")
public interface LimitClient {

    @PutMapping("/user/{userId}")
    boolean updateLimit(@PathVariable Long userId, @RequestParam BigDecimal amount);
}
