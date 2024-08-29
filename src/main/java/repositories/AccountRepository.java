package repositories;

import entity.account.Account;
import entity.usuario.Usuario;
import globals.Persistence;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import models.account.AccountDTO;
import models.account.AccountForm;
import utils.ExceptionUtils;
import java.util.Collections;
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
        try {
            String query = "SELECT new models.account.AccountDTO(a.id, a.name, a.type, a.amount, a.installments, a.startDate, a.endDate, a.createdAt) FROM Account a WHERE a.user.id = :userId";
            return persistence.createQuery(query, AccountDTO.class)
                    .setParameter("userId", userId)
                    .getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public AccountDTO getAccountById(UUID id) {
        try {
            String query = "SELECT new models.account.AccountDTO(a.id, a.name, a.type, a.amount, a.installments, a.startDate, a.endDate, a.createdAt) FROM Account a WHERE a.id = :id";
            return persistence.createQuery(query, AccountDTO.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            ExceptionUtils.throwBadRequestException("Conta não encontrada.");
            return null;
        }
    }

    public void updateAccount(UUID id, AccountForm account) {
        try {
            Account accountToUpdate = persistence.find(Account.class, id);
            if (accountToUpdate == null) {
                ExceptionUtils.throwBadRequestException("Conta não encontrada.");
            }

            accountToUpdate.setName(account.getName());
            accountToUpdate.setType(account.getType());
            accountToUpdate.setAmount(account.getAmount());
            accountToUpdate.setInstallments(account.getInstallments());
            accountToUpdate.setStartDate(account.getStartDate());
            accountToUpdate.setEndDate(account.getEndDate());
            persistence.merge(accountToUpdate);
        } catch (Exception e) {
            Log.error("Erro ao atualizar conta.", e);
            ExceptionUtils.throwInternalErrorException("Erro interno no servidor.");
        }
    }

    public void deleteAccount(UUID id) {
        try {
            Account account = persistence.find(Account.class, id);
            persistence.remove(account);
        } catch (Exception e) {
            ExceptionUtils.throwBadRequestException("Conta não encontrada.");
        }
    }
}
