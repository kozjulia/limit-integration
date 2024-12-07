package ru.t1.limit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.t1.limit.model.Limit;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author YKozlova
 */
public interface LimitRepository extends JpaRepository<Limit, Long> {

    void deleteByUserId(Long userId);

    Optional<Limit> findByUserId(Long userId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Limit SET limitAmount = :defaultLimit ")
    void updateAllLimits(BigDecimal defaultLimit);
}
