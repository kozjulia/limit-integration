package ru.t1.limit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.t1.limit.service.LimitService;
import t1.dto.module.dto.LimitDto;

import java.math.BigDecimal;

/**
 * @author YKozlova
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/limits")
@Tag(name = "Лимиты", description = "Взаимодействие с лимитами пользователей")
public class LimitController {

    private final LimitService limitService;

    @PostMapping("/user/{userId}")
    @Operation(
            summary = "Добавить лимит пользователя",
            description = "Позволяет добавить новый лимит пользователя")
    /**
     * Создание лимита пользователя
     */
    public LimitDto create(@PathVariable Long userId) {

        LimitDto limitDto = limitService.create(userId);
        log.debug("Добавлен новый лимит пользователя: {}", limitDto);
        return limitDto;
    }

    @PutMapping("/user/{userId}")
    @Operation(
            summary = "Обновить пользователя",
            description = "Позволяет обновить пользователя")
    /**
     * Обновление пользователя
     */
    public boolean update(@PathVariable Long userId, @RequestParam BigDecimal amount) {

        boolean result = limitService.update(userId, amount);
        log.debug("Обновлен лимит пользователя с id = {} на размер {}", userId, amount);
        return result;
    }

    @DeleteMapping("/user/{userId}")
    @Operation(
            summary = "Удалить лимит по id пользователя",
            description = "Позволяет удалить лимит по id пользователя")
    /**
     * Удаление лимита по id пользователя
     */
    public void deleteByUserId(@PathVariable Long userId) {

        limitService.deleteByUserId(userId);
        log.debug("Удалён лимит c id пользователя: {}", userId);
    }

    @GetMapping("/user/{userId}")
    @Operation(
            summary = "Найти лимит по id пользователя",
            description = "Позволяет найти лимит по id пользователя")
    /**
     * Получение лимита по id пользователя
     */
    public LimitDto findByUserId(@PathVariable Long userId) {

        LimitDto limitDto = limitService.findByUserId(userId);
        log.debug("Получен лимит пользователя с id = {}: {}", userId, limitDto);
        return limitDto;
    }
}
