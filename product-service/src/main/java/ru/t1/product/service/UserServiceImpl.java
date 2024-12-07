package ru.t1.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.t1.product.client.LimitClient;
import ru.t1.product.exception.UserException;
import ru.t1.product.model.User;
import ru.t1.product.repository.UserRepository;

import java.util.List;
import java.util.Objects;

/**
 * @author YKozlova
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LimitClient limitClient;

    @Override
    public User create(User user) {

        try {
            User newUser = userRepository.save(user);
            limitClient.createLimit(newUser.getId());
            log.info("Создан пользователь: {}.", newUser);
            return newUser;
        } catch (Exception exc) {
            throw new UserException("Пользователь не создан с username = " + user.getUsername());
        }
    }

    @Override
    public boolean update(Long id, String username) {

        User user = userRepository.findById(id).orElse(null);

        if (Objects.isNull(user)) {
            log.info("У пользователя с id = {} не обновлено username на {}, пользователь не найден.", id, username);
            return false;
        }

        user.setUsername(username);
        userRepository.save(user);
        log.info("У пользователя с id = {} обновлено username на {}.", id, username);
        return true;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
        limitClient.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new UserException("Пользователь с id = " + id + " не найден.")
        );
        log.info("Найден пользователь с id = {}: {}.", id, user);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {

        List<User> users = userRepository.findAll();
        log.info("Найдено {} пользователя/-ей.", users.size());
        return users;
    }
}
