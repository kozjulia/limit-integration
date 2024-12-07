package ru.t1.payment.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.t1.payment.service.PaymentService;
import t1.dto.module.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author YKozlova
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
@Tag(name = "Оплаты", description = "Взаимодействие с оплатами")
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * Получение списка всех продуктов
     */
    @GetMapping("/products")
    public List<ProductDto> findAllProducts() {

        return paymentService.findAllProducts();
    }

    /**
     * Исполнить платеж
     *
     * @param productId productId продукта
     * @param balance   Размер платежа
     * @return результат исполнения команды
     */
    @PutMapping("/products/{productId}")
    public boolean executePayment(@PathVariable Long productId, @RequestParam @Positive BigDecimal balance) {

        return paymentService.executePayment(productId, balance);
    }
}
