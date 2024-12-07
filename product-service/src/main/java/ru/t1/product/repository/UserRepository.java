package ru.t1.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.t1.product.model.User;

/**
 * @author YKozlova
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
