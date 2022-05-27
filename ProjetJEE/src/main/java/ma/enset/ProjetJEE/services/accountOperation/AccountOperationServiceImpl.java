package ma.enset.ProjetJEE.services.accountOperation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ma.enset.ProjetJEE.dtos.AccountOperationDTO;
import ma.enset.ProjetJEE.entities.AccountOperation;
import ma.enset.ProjetJEE.entities.BankAccount;
import ma.enset.ProjetJEE.enums.OperationType;
import ma.enset.ProjetJEE.exceptions.BalanceNotSufficientEception;
import ma.enset.ProjetJEE.exceptions.BankAccountNotFoundException;
import ma.enset.ProjetJEE.mappers.BankAccountMappersService;
import ma.enset.ProjetJEE.repositories.AccountOperationRepository;
import ma.enset.ProjetJEE.repositories.BankAccountRepository;

@Service
@Transactional
public class AccountOperationServiceImpl implements AccountOperationService {
	private AccountOperationRepository accountOperationRepository;
	private BankAccountRepository bankAccountRepository;
	private BankAccountMappersService bankAccountMappersService;
	public AccountOperationServiceImpl(BankAccountRepository bankAccountRepository,
			AccountOperationRepository accountOperationRepository, BankAccountMappersService bankAccountMappersService) {

		this.bankAccountRepository = bankAccountRepository;
		this.accountOperationRepository = accountOperationRepository;
		this.bankAccountMappersService = bankAccountMappersService;
	}
	@Override
	public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientEception {
		BankAccount bankAccount = bankAccountRepository.findById(accountId)
				.orElseThrow(()-> new BankAccountNotFoundException("BankAccount not found"));
		if(bankAccount.getBalance()<amount)
			throw new BalanceNotSufficientEception("Balance not sufficient");
		AccountOperation accountOperation = new AccountOperation();
		accountOperation.setType(OperationType.DEBIT);
		accountOperation.setAmount(amount);
		accountOperation.setOperationDate(new Date());
		accountOperation.setBankAccount(bankAccount);
		accountOperation.setDescription(description);
		accountOperationRepository.save(accountOperation);
		bankAccount.setBalance(bankAccount.getBalance()-amount);
		bankAccountRepository.save(bankAccount);
	}
	@Override
	public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {
		BankAccount bankAccount = bankAccountRepository.findById(accountId)
				.orElseThrow(()-> new BankAccountNotFoundException("BankAccount not found"));
		AccountOperation accountOperation = new AccountOperation();
		accountOperation.setType(OperationType.CREDIT);
		accountOperation.setAmount(amount);
		accountOperation.setOperationDate(new Date());
		accountOperation.setBankAccount(bankAccount);
		accountOperation.setDescription(description);
		accountOperationRepository.save(accountOperation);
		bankAccount.setBalance(bankAccount.getBalance()+amount);
		bankAccountRepository.save(bankAccount);
	}

	@Override
	public void transfer(String accountIdSource, String accountDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientEception {
		debit(accountIdSource, amount, "transfer to "+accountDestination);
		credit(accountDestination, amount, "transfer from"+accountIdSource);
		
	}
	@Override
	public List<AccountOperationDTO> accountOperationHistory(String accountId) {
		List<AccountOperation> accountOperations = accountOperationRepository.findByBankAccountId(accountId);
		return accountOperations.stream().map(op-> bankAccountMappersService.fromAccountOperation(op)).collect(Collectors.toList());
	}
	@Override
	public AccountOperationDTO getAccountOperationDTO(Long id) {
		AccountOperation accountOperation = accountOperationRepository.findById(id).get();
		return bankAccountMappersService.fromAccountOperation(accountOperation);
	}
	@Override
	public AccountOperationDTO updateAccountOperationDTO(AccountOperationDTO accountOperationDTO) {
		AccountOperation accountOperation = bankAccountMappersService.fromAccountOperationDTO(accountOperationDTO);
		accountOperation.setBankAccount(bankAccountMappersService.fromBankAccountDTO(accountOperationDTO.getBankAccountDTO()));
		AccountOperation savedAccountOperation = accountOperationRepository.save(accountOperation);
		return bankAccountMappersService.fromAccountOperation(savedAccountOperation);
	}
	@Override
	public void deleteAccountOperation(Long id) {
		accountOperationRepository.deleteById(id);		
	}
	@Override
	public List<AccountOperationDTO> listAccountOperationDTO() {
		List<AccountOperation> accountOperations = accountOperationRepository.findAll();
		List<AccountOperationDTO> accountOperationsDTO = accountOperations.stream().map(accountOperation -> bankAccountMappersService.fromAccountOperation(accountOperation)).collect(Collectors.toList());
		return accountOperationsDTO;
	}
	@Override
	public List<AccountOperationDTO> listBankAccountOperations(String id) {
		BankAccount bankAccount = bankAccountRepository.getById(id);
		List<AccountOperation> accountOperations = bankAccount.getAccountOperations();
		List<AccountOperationDTO> accountOperationDTOs = accountOperations.stream().map(acc->bankAccountMappersService.fromAccountOperation(acc)).collect(Collectors.toList());
		return accountOperationDTOs;
	}
	@Override
	public List<AccountOperationDTO> searchAccountOperations(String keyword) {
		List<AccountOperation> accountOperations = accountOperationRepository.searchAccountOperations(keyword);
		List<AccountOperationDTO> accountOperationDTOs= accountOperations.stream().map(account->bankAccountMappersService.fromAccountOperation(account)).collect(Collectors.toList());
		return accountOperationDTOs;
	}
	
}
