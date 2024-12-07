package t1.dto.module.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author YKozlova
 */
@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private AccountType accountType;
    private Long userId;
}
