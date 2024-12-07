package ru.t1.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.t1.product.model.Product;

import java.util.List;

/**
 * @author YKozlova
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByUserId(Long userId);
}
