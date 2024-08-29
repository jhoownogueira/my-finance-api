package services.account;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import models.account.AccountDTO;
import models.account.AccountForm;
import repository.account.AccountRepository;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AccountService {

    @Inject
    AccountRepository accountRepository;

    public void createAccount(AccountForm account) {
        accountRepository.createAccount(account);
    }

    public List<AccountDTO> getAccountsByUserId(UUID userId) {
        return accountRepository.getAccountsByUserId(userId);
    }

    public AccountDTO getAccountById(UUID id) {
        return accountRepository.getAccountById(id);
    }
}
