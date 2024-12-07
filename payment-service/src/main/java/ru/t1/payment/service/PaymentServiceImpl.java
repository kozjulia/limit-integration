package ru.t1.payment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.t1.payment.client.LimitClient;
import ru.t1.payment.client.ProductClient;
import t1.dto.module.dto.ProductDto;
import t1.dto.module.dto.ProductException;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author YKozlova
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final ProductClient productClient;
    private final LimitClient limitClient;

    @Override
    public List<ProductDto> findAllProducts() {
        List<ProductDto> products = productClient.findAllProducts();
        log.debug("Получен список продуктов, количество = {}", products.size());
        return products;
    }

    @Override
    public boolean executePayment(Long productId, BigDecimal balance) {
        ProductDto product;

        try {
            product = productClient.findProductById(productId);
        } catch (Exception exception) {
            throw new ProductException("Продукт с id = " + productId + " не найден.");
        }

        if (balance.compareTo(product.getBalance()) > 0) {
            throw new ProductException("У продукта с id = " + productId + " недостаточен баланс для списания " + balance);
        }

        Long userId = product.getUserId();
        try {
            limitClient.updateLimit(userId, balance.negate());
        } catch (Exception e) {
            log.info("У продукта с id = {} не уменьшен balance на {}, дневной лимит исчерпан.", userId, balance);
            return false;
        }

        try {
            boolean result = productClient.updateProduct(productId, balance.negate());
            log.info("Результат обновления баланса на {} продукта с id  = {}: {}", balance, productId, result);
            return result;
        } catch (Exception exception) {
            limitClient.updateLimit(userId, balance);
            return false;
        }
    }
}
