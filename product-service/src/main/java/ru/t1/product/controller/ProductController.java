package ru.t1.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
import ru.t1.product.model.NewProductDto;
import ru.t1.product.service.ProductService;
import t1.dto.module.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author YKozlova
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Tag(name = "Продукты", description = "Взаимодействие с продуктами")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/{userId}")
    @Operation(
            summary = "Добавить продукт",
            description = "Позволяет добавить новый продукт")
    /**
     * Создание продукта
     */
    public ProductDto create(@Valid @RequestBody NewProductDto newProductDto, @PathVariable Long userId) {

        ProductDto productDto = productService.create(newProductDto, userId);
        log.debug("Добавлен новый продукт с id = {} пользователя с id = {}", productDto.getId(), userId);
        return productDto;
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновить продукт",
            description = "Позволяет обновить продукт")
    /**
     * Обновление продукта
     */
    public boolean update(@PathVariable Long id, @RequestParam BigDecimal balance) {

        boolean result = productService.update(id, balance);
        log.debug("Обновлен продукт с id = {}, balance = {}", id, balance);
        return result;
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить продукт по id",
            description = "Позволяет удалить продукт по id")
    /**
     * Удаление продукта по id
     */
    public void deleteById(@PathVariable Long id) {

        productService.deleteById(id);
        log.debug("Удалён продукт c id: {}", id);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Найти продукт по id",
            description = "Позволяет найти продукт по id")
    /**
     * Получение продукта по id
     */
    public ProductDto findById(@PathVariable Long id) {

        ProductDto product = productService.findById(id);
        log.debug("Получен продукт с id = {}: {}", id, product);
        return product;
    }

    @GetMapping
    @Operation(
            summary = "Найти все продукты",
            description = "Позволяет найти все продукты")
    /**
     * Получение списка всех продуктов
     */
    public List<ProductDto> findAll() {

        List<ProductDto> products = productService.findAll();
        log.debug("Получен список продуктов, количество = {}", products.size());
        return products;
    }

    @GetMapping("/user/{userId}")
    @Operation(
            summary = "Найти все продукты пользователя",
            description = "Позволяет найти все продукты пользователя")
    /**
     * Получение списка всех продуктов пользователя
     */
    public List<ProductDto> findAllByUserId(@PathVariable Long userId) {

        List<ProductDto> products = productService.findAllByUserId(userId);
        log.debug("Получен список продуктов пользоватлея с id = {}, количество = {}", userId, products.size());
        return products;
    }
}
