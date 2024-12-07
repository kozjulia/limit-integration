package ru.t1.limit.service;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.t1.limit.exception.LimitException;
import ru.t1.limit.mapper.LimitMapper;
import ru.t1.limit.model.Limit;
import ru.t1.limit.repository.LimitRepository;
import t1.dto.module.dto.LimitDto;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author YKozlova
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LimitServiceImpl implements LimitService {

    private final LimitRepository limitRepository;
    private final LimitMapper limitMapper;

    @Value("${user.default-limit}")
    private int defaultLimit;

    @Override
    @Nullable
    public LimitDto create(Long userId) {

        Limit limit = Limit.builder()
                .limitAmount(new BigDecimal(defaultLimit))
                .userId(userId)
                .build();
        try {
            return limitMapper.toLimitDto(limitRepository.save(limit));
        } catch (Exception exception) {
            log.error("Не удалось создать лимит для пользователя с id = {}: {}", userId, exception.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Long userId, BigDecimal amount) {

        LimitDto limitDto = findByUserId(userId);

        if (limitDto.getLimitAmount().compareTo(amount.abs()) < 0) {
            throw new LimitException("У лимита пользователя с id = " + userId + " дневной лимит исчерпан = "
                    + limitDto.getLimitAmount() + " для списания " + amount);
        }

        Limit limit = limitRepository.findByUserId(userId).get();
        limit.setLimitAmount(limit.getLimitAmount().add(amount));
        limitRepository.save(limit);
        return true;
    }

    @Override
    public void updateAllLimits() {
        limitRepository.updateAllLimits(new BigDecimal(defaultLimit));
    }

    @Override
    public void deleteByUserId(Long userId) {
        limitRepository.deleteByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public LimitDto findByUserId(Long userId) {

        Optional<Limit> limit = limitRepository.findByUserId(userId);

        if (limit.isPresent()) {
            return limitMapper.toLimitDto(limit.get());
        } else {
            return create(userId);
        }
    }
}
