package ma.enset.ProjetJEE.services.bankAccount;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ma.enset.ProjetJEE.dtos.AccountHistoryDTO;
import ma.enset.ProjetJEE.dtos.AccountOperationDTO;
import ma.enset.ProjetJEE.dtos.BankAccountDTO;
import ma.enset.ProjetJEE.dtos.CurrentAccountDTO;
import ma.enset.ProjetJEE.dtos.SavingAccountDTO;
import ma.enset.ProjetJEE.entities.AccountOperation;
import ma.enset.ProjetJEE.entities.BankAccount;
import ma.enset.ProjetJEE.entities.CurrentAccount;
import ma.enset.ProjetJEE.entities.Customer;
import ma.enset.ProjetJEE.entities.SavingAccount;
import ma.enset.ProjetJEE.exceptions.BankAccountNotFoundException;
import ma.enset.ProjetJEE.exceptions.CustomerNotFoundException;
import ma.enset.ProjetJEE.mappers.BankAccountMappersService;
import ma.enset.ProjetJEE.repositories.AccountOperationRepository;
import ma.enset.ProjetJEE.repositories.BankAccountRepository;
import ma.enset.ProjetJEE.repositories.CustomerRepository;

@Service
@Transactional 
//@Slf4j pour loguer les messages
//log4j pour al journalisation 
//slf4j egalement 
public class BankAccountServiceImpl implements BankAccountService {
	private CustomerRepository customerRepository;
	private BankAccountRepository bankAccountRepository;
	private AccountOperationRepository accountOperationRepository;
	private BankAccountMappersService bankAccountMappersService;
	
	//@Slf4j = Logger log = LoggerFactory.getLooger(this.getClass().getName());
	//constructeur pour l'injection des dependances
	public BankAccountServiceImpl(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository,
			AccountOperationRepository accountOperationRepository, BankAccountMappersService bankAccountMappersService) {
		this.customerRepository = customerRepository;
		this.bankAccountRepository = bankAccountRepository;
		this.accountOperationRepository = accountOperationRepository;
		this.bankAccountMappersService = bankAccountMappersService;
	}	

	@Override
	public BankAccountDTO getBankAccountDTO(Long accountId) throws BankAccountNotFoundException {
		BankAccount bankAccount = bankAccountRepository.findById(accountId)
				.orElseThrow(()-> new BankAccountNotFoundException("BankAccount not found"));
		if(bankAccount instanceof SavingAccount) {
			SavingAccount savingAccount = (SavingAccount) bankAccount;
			return bankAccountMappersService.fromSavingAccount(savingAccount);
		} else if(bankAccount instanceof CurrentAccount) {
			CurrentAccount currentAccount = (CurrentAccount) bankAccount;
			return bankAccountMappersService.fromCurrentAccount(currentAccount);
		}
		return null;
		
	}

	@Override
	public List<BankAccountDTO> listCustomerBankAccounts(Long id) {
		Customer customer = customerRepository.getById(id);
		List<BankAccount> bankAccounts = customer.getBankAccounts();
		List<BankAccountDTO> bankAccountDTOs = bankAccounts.stream().map(acc->bankAccountMappersService.fromBankAccount(acc)).collect(Collectors.toList());
		return bankAccountDTOs;
	}
	
	@Override
	public CurrentAccountDTO saveCurrentBankAccount(BankAccountDTO bankAccountDTO)
			throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(bankAccountDTO.getCustomerDTO().getId()).orElse(null);
		if ( customer == null ) {
			throw new CustomerNotFoundException("Customer not found");
		}
		CurrentAccount currentAccount = new CurrentAccount();
		currentAccount.setCreatedAt(new Date());
		currentAccount.setBalance(bankAccountDTO.getBalance());
		currentAccount.setCustomer(customer);
		currentAccount.setDescription(bankAccountDTO.getDescription());
		currentAccount.setStatus(bankAccountDTO.getStatus());
		currentAccount.setOverDraft(bankAccountDTO.getOverDraft());
		CurrentAccount savedBankAccount = bankAccountRepository.save(currentAccount);
		return bankAccountMappersService.fromCurrentAccount(savedBankAccount);
	}

	@Override
	public SavingAccountDTO saveSavingBankAccount(BankAccountDTO bankAccountDTO)
			throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(bankAccountDTO.getCustomerDTO().getId()).orElse(null);
		if ( customer == null ) {
			throw new CustomerNotFoundException("Customer not found");
		}
		SavingAccount savingAccount = new SavingAccount();
		savingAccount.setCreatedAt(new Date());
		savingAccount.setDescription(bankAccountDTO.getDescription());
		savingAccount.setBalance(bankAccountDTO.getBalance());
		savingAccount.setCustomer(customer);
		savingAccount.setStatus(bankAccountDTO.getStatus());
		savingAccount.setInterestRate(bankAccountDTO.getInterestRate());
		SavingAccount savedBankAccount = bankAccountRepository.save(savingAccount);
		return bankAccountMappersService.fromSavingAccount(savedBankAccount);
	}

	@Override
	public List<BankAccountDTO> bankAccountListDTO() {
		List<BankAccount> bankAccounts = bankAccountRepository.findAll();
		List<BankAccountDTO> bankAccountDTOs = bankAccounts.stream().map(bankAccount ->{
			if(bankAccount instanceof SavingAccount) {
				SavingAccount savingAccount = (SavingAccount) bankAccount;
				return bankAccountMappersService.fromSavingAccount(savingAccount);
			} else {
				CurrentAccount currentAccount = (CurrentAccount) bankAccount;
				return bankAccountMappersService.fromCurrentAccount(currentAccount);
			}
		}).collect(Collectors.toList());
	return bankAccountDTOs;
	}

	@Override
	public void deleteBankAccount(Long accountId) {
		bankAccountRepository.deleteById(accountId);
	}

	@Override
	public AccountHistoryDTO getAccountHistory(Long accountId, int page, int size) throws BankAccountNotFoundException {
		BankAccount bankAccount = bankAccountRepository.findById(accountId).orElse(null);
		if(bankAccount == null) throw new BankAccountNotFoundException("Account not found");
		Page<AccountOperation> accountOperations = accountOperationRepository.findByBankAccountId(accountId, PageRequest.of(page, size));
		AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO();
		List<AccountOperationDTO> accountOperationDTOs= accountOperations.getContent().stream().map(op-> bankAccountMappersService.fromAccountOperation(op)).collect(Collectors.toList());
		accountHistoryDTO.setAccountOperationDTOs(accountOperationDTOs);
		accountHistoryDTO.setAccountId(bankAccount.getId());
		accountHistoryDTO.setBakance(bankAccount.getBalance());
		accountHistoryDTO.setPageSize(page);
		accountHistoryDTO.setPageSize(size);
		accountHistoryDTO.setTotalPages(accountOperations.getTotalPages());
		return accountHistoryDTO;
	}

	@Override
	public void updateBankAccountDTO(BankAccountDTO bankAccountDTO){
		if(bankAccountDTO.getType().equals("CurrentAccount") && bankAccountDTO.getInterestRate()==0) {
			CurrentAccount currentAccount = new CurrentAccount();
			BeanUtils.copyProperties(bankAccountDTO, currentAccount,"interestRate");
			currentAccount.setCustomer(bankAccountMappersService.fromCustomerDTO(bankAccountDTO.getCustomerDTO()));
			currentAccount.setAccountOperations(null);
			currentAccount.setOverDraft(bankAccountDTO.getOverDraft());
			 bankAccountMappersService.fromCurrentAccount(currentAccount);
			 bankAccountRepository.save(currentAccount);
		} else {
			SavingAccount savingAccount = new SavingAccount();
			BeanUtils.copyProperties(bankAccountDTO, savingAccount,"overDraft");
			savingAccount.setAccountOperations(null);
			savingAccount.setCustomer(bankAccountMappersService.fromCustomerDTO(bankAccountDTO.getCustomerDTO()));
			savingAccount.setInterestRate(bankAccountDTO.getInterestRate());
			bankAccountMappersService.fromSavingAccount(savingAccount);
			 bankAccountRepository.save(savingAccount);
		}
	}
	@Override
	public List<BankAccountDTO> searchBankAccounts(String keyword) {
		List<BankAccount> bankAccounts = bankAccountRepository.searchBankAccounts(keyword);
		List<BankAccountDTO> bankAccountDTOs= bankAccounts.stream().map(bank->bankAccountMappersService.fromBankAccount(bank)).collect(Collectors.toList());
		return bankAccountDTOs;
	}
	
}
