package ru.t1.payment.service;

import t1.dto.module.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author YKozlova
 */
public interface PaymentService {

    List<ProductDto> findAllProducts();

    boolean executePayment(Long productId, BigDecimal balance);
}
