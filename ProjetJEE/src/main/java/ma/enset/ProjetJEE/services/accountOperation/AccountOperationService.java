package ma.enset.ProjetJEE.services.accountOperation;

import java.util.List;

import ma.enset.ProjetJEE.dtos.AccountOperationDTO;
import ma.enset.ProjetJEE.exceptions.BalanceNotSufficientEception;
import ma.enset.ProjetJEE.exceptions.BankAccountNotFoundException;

public interface AccountOperationService {
	void debit(Long accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientEception;
	void credit(Long accountId, double amount, String description)  throws BankAccountNotFoundException;
	void transfer(Long accountIdSource, Long accountDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientEception;
	List<AccountOperationDTO> accountOperationHistory(Long accountId);
	AccountOperationDTO getAccountOperationDTO(Long id);
	AccountOperationDTO updateAccountOperationDTO(AccountOperationDTO accountOperationDTO);
	void deleteAccountOperation(Long id);
	List<AccountOperationDTO> searchAccountOperations(String keyword);
	List<AccountOperationDTO> listAccountOperationDTO();
	List<AccountOperationDTO> listBankAccountOperations(Long id);

}
