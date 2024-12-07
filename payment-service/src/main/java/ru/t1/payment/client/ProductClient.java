package ru.t1.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import t1.dto.module.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * Клиент для работы с сервисом продуктов
 *
 * @author YKozlova
 */
@FeignClient(name = "product-client", url = "${integration.product-service.url}", path = "/products")
public interface ProductClient {

    @GetMapping
    List<ProductDto> findAllProducts();

    @GetMapping("/{productId}")
    ProductDto findProductById(@PathVariable Long productId);

    @PutMapping("/{productId}")
    boolean updateProduct(@PathVariable Long productId, @RequestParam BigDecimal balance);
}
