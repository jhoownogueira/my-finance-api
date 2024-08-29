package models.account;

import entity.account.AccountType;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class AccountDTO {

    private UUID id;
    private String name;
    private AccountType type;
    private BigDecimal amount;
    private Integer installments;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;

    public AccountDTO(UUID id, String name, AccountType type, BigDecimal amount, Integer installments, LocalDate startDate, LocalDate endDate, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.installments = installments;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
    }
}
