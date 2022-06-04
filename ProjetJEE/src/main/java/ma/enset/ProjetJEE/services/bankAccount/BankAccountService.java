package ma.enset.ProjetJEE.services.bankAccount;

import java.util.List;

import ma.enset.ProjetJEE.exceptions.BankAccountNotFoundException;
import ma.enset.ProjetJEE.exceptions.CustomerNotFoundException;
import ma.enset.ProjetJEE.dtos.AccountHistoryDTO;
import ma.enset.ProjetJEE.dtos.BankAccountDTO;
import ma.enset.ProjetJEE.dtos.CurrentAccountDTO;
import ma.enset.ProjetJEE.dtos.SavingAccountDTO;

public interface BankAccountService {
	void deleteBankAccount(Long accountId);
	CurrentAccountDTO saveCurrentBankAccount(BankAccountDTO bankAccountDTO) throws CustomerNotFoundException;
	SavingAccountDTO saveSavingBankAccount(BankAccountDTO bankAccountDTO) throws CustomerNotFoundException;
	void updateBankAccountDTO(BankAccountDTO bankAccountDTO);
	BankAccountDTO getBankAccountDTO(Long id) throws BankAccountNotFoundException;
	List<BankAccountDTO> bankAccountListDTO();
	List<BankAccountDTO> searchBankAccounts(String keyword);
	AccountHistoryDTO getAccountHistory(Long accountId, int page, int size) throws BankAccountNotFoundException;
	List<BankAccountDTO> listCustomerBankAccounts(Long id);
}
