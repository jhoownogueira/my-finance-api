package models.account;

import entity.account.AccountType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class AccountForm {

    @NotNull(message = "User id is required")
    private UUID userId;

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Type is required")
    private AccountType type;

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @NotNull(message = "Installments is required")
    private Integer installments;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;
}
