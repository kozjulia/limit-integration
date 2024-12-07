package ru.t1.product.service;

import ru.t1.product.model.User;

import java.util.List;

/**
 * @author YKozlova
 */
public interface UserService {

    User create(User user);

    boolean update(Long id, String username);

    void deleteById(Long id);

    User findById(Long id);

    List<User> findAll();
}
