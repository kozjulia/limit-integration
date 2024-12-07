package ru.t1.product.service;

import ru.t1.product.model.NewProductDto;
import t1.dto.module.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author YKozlova
 */
public interface ProductService {

    ProductDto create(NewProductDto productDto, Long userId);

    boolean update(Long id,  BigDecimal balance);

    void deleteById(Long id);

    ProductDto findById(Long id);

    List<ProductDto> findAll();

    List<ProductDto> findAllByUserId(Long userId);
}
