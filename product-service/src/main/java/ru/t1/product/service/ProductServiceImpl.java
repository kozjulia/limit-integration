package ru.t1.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.t1.product.client.LimitClient;
import ru.t1.product.model.NewProductDto;
import ru.t1.product.model.Product;
import ru.t1.product.model.User;
import ru.t1.product.model.mapper.ProductMapper;
import ru.t1.product.repository.ProductRepository;
import t1.dto.module.dto.ProductDto;
import t1.dto.module.dto.ProductException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @author YKozlova
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserService userService;
    private final LimitClient limitClient;

    @Override
    public ProductDto create(NewProductDto productDto, Long userId) {

        User user = userService.findById(userId);

        try {
            Product product = productRepository.save(productMapper.toProduct(productDto, user));
            log.info("Создан продукт с id = {} пользователя с id = {}.", product.getId(), userId);
            return productMapper.toProductDto(product);
        } catch (Exception exc) {
            throw new ProductException("Продукт не создан с accountNumber = " + productDto.getAccountNumber());
        }
    }

    @Override
    public boolean update(Long id, BigDecimal balance) {

        Product product = productRepository.findById(id).orElse(null);

        if (Objects.isNull(product)) {
            log.info("У продукта с id = {} не уменьшен balance на {}, продукт не найден.", id, balance);
            return false;
        }

        product.setBalance(product.getBalance().add(balance));
        productRepository.save(product);
        log.info("У продукта с id = {} уменьшен balance на {}.", id, balance);
        return true;
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {

        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductException("Продукт с id = " + id + " не найден.")
        );
        log.info("Найден продукт с id = {}: {}.", id, product);
        return productMapper.toProductDto(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> findAll() {

        List<Product> products = productRepository.findAll();
        log.info("Найдено {} продукта/-ов.", products.size());
        return productMapper.convertProductListToProductDtoList(products);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> findAllByUserId(Long userId) {

        List<Product> products = productRepository.findAllByUserId(userId);
        log.info("Найдено {} продукта/-ов.", products.size());
        return productMapper.convertProductListToProductDtoList(products);
    }
}
