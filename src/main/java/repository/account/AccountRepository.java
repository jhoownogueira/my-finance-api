package repository.account;

import entity.Account;
import entity.Usuario;
import globals.Persistence;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import models.account.AccountDTO;
import models.account.AccountForm;
import utils.ExceptionUtils;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AccountRepository extends Persistence {

    public void createAccount(AccountForm account) {
        try {

            Usuario user = persistence.find(Usuario.class, account.getUserId());
            if (user == null) {
                ExceptionUtils.throwBadRequestException("Usuário não encontrado.");
            }

            Account newAccount = new Account();
            newAccount.setUser(user);
            newAccount.setName(account.getName());
            newAccount.setType(account.getType());
            newAccount.setAmount(account.getAmount());
            newAccount.setInstallments(account.getInstallments());
            newAccount.setStartDate(account.getStartDate());
            newAccount.setEndDate(account.getEndDate());
            persistence.persist(newAccount);
        } catch (Exception e) {
            Log.error("Erro ao criar conta.", e);
            ExceptionUtils.throwInternalErrorException("Erro interno no servidor.");
        }
    }

    public List<AccountDTO> getAccountsByUserId(UUID userId) {
        String query = "SELECT new models.account.AccountDTO(a.id, a.name, a.type, a.amount, a.installments, a.startDate, a.endDate, a.createdAt) FROM Account a WHERE a.user.id = :userId";
        return persistence.createQuery(query, AccountDTO.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public AccountDTO getAccountById(UUID id) {
        String query = "SELECT new models.account.AccountDTO(a.id, a.name, a.type, a.amount, a.installments, a.startDate, a.endDate, a.createdAt) FROM Account a WHERE a.id = :id";
        return persistence.createQuery(query, AccountDTO.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
