package ma.enset.ProjetJEE.services.accountOperation;

import java.util.List;

import ma.enset.ProjetJEE.dtos.AccountOperationDTO;
import ma.enset.ProjetJEE.exceptions.BalanceNotSufficientEception;
import ma.enset.ProjetJEE.exceptions.BankAccountNotFoundException;

public interface AccountOperationService {
	void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientEception;
	void credit(String accountId, double amount, String description)  throws BankAccountNotFoundException;
	void transfer(String accountIdSource, String accountDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientEception;
	List<AccountOperationDTO> accountOperationHistory(String accountId);
	AccountOperationDTO getAccountOperationDTO(Long id);
	AccountOperationDTO updateAccountOperationDTO(AccountOperationDTO accountOperationDTO);
	void deleteAccountOperation(Long id);
	List<AccountOperationDTO> searchAccountOperations(String keyword);
	List<AccountOperationDTO> listAccountOperationDTO();
	List<AccountOperationDTO> listBankAccountOperations(String id);

}
