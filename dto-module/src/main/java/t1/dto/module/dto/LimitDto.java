package t1.dto.module.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author YKozlova
 */
@Data
@NoArgsConstructor
public class LimitDto {

    private BigDecimal limitAmount;
    private Long userId;
}
