package ru.t1.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.t1.product.model.User;
import ru.t1.product.service.UserService;

import java.util.List;

/**
 * @author YKozlova
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "Взаимодействие с пользователями")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(
            summary = "Добавить пользователя",
            description = "Позволяет добавить нового пользователя")
    /**
     * Создание пользователя
     */
    public User create(@Valid @RequestBody User user) {

        User newUser = userService.create(user);
        log.debug("Добавлен новый пользователь: {}", newUser);
        return newUser;
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновить пользователя",
            description = "Позволяет обновить пользователя")
    /**
     * Обновление пользователя
     */
    public boolean update(@PathVariable Long id, @NotEmpty @RequestParam String username) {

        boolean result = userService.update(id, username);
        log.debug("Обновлен пользователь с id = {}, username = {}", id, username);
        return result;
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить пользователя по id",
            description = "Позволяет удалить пользователя по id")
    /**
     * Удаление пользователя по id
     */
    public void deleteById(@PathVariable Long id) {

        userService.deleteById(id);
        log.debug("Удалён пользователь c id: {}", id);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Найти пользователя по id",
            description = "Позволяет найти пользователя по id")
    /**
     * Получение пользователя по id
     */
    public User findById(@PathVariable Long id) {

        User user = userService.findById(id);
        log.debug("Получен пользователь с id = {}: {}", id, user);
        return user;
    }

    @GetMapping
    @Operation(
            summary = "Найти всех пользователей",
            description = "Позволяет найти всех пользователей")
    /**
     * Получение списка всех пользователей
     */
    public List<User> findAll() {

        List<User> users = userService.findAll();
        log.debug("Получен список пользователей, количество = {}", users.size());
        return users;
    }
}
