package ru.t1.product.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import t1.dto.module.dto.LimitDto;

/**
 * Клиент для работы с сервисом лимитов
 *
 * @author YKozlova
 */
@FeignClient(name = "limit-client", url = "${integration.limit-service.url}", path = "/limits")
public interface LimitClient {

    @PostMapping("/user/{userId}")
    LimitDto createLimit(@PathVariable Long userId);

    @DeleteMapping("/user/{userId}")
    void deleteById(@PathVariable Long userId);

    @GetMapping("/user/{userId}")
    void findById(@PathVariable Long userId);
}
