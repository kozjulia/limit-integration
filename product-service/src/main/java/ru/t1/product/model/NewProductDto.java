package ru.t1.product.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import t1.dto.module.dto.AccountType;

import java.math.BigDecimal;

/**
 * @author YKozlova
 */
@Data
@Builder
public class NewProductDto {

    @NotEmpty
    private String accountNumber;

    @Min(value = 0)
    private BigDecimal balance;

    private AccountType accountType;
}
