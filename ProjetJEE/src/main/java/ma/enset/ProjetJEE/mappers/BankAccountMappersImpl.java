package ma.enset.ProjetJEE.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import ma.enset.ProjetJEE.dtos.AccountOperationDTO;
import ma.enset.ProjetJEE.dtos.BankAccountDTO;
import ma.enset.ProjetJEE.dtos.CurrentAccountDTO;
import ma.enset.ProjetJEE.dtos.CustomerDTO;
import ma.enset.ProjetJEE.dtos.SavingAccountDTO;
import ma.enset.ProjetJEE.entities.AccountOperation;
import ma.enset.ProjetJEE.entities.BankAccount;
import ma.enset.ProjetJEE.entities.CurrentAccount;
import ma.enset.ProjetJEE.entities.Customer;
import ma.enset.ProjetJEE.entities.SavingAccount;

@Service
public class BankAccountMappersImpl implements BankAccountMappersService {
	@Override
	public CustomerDTO fromCustomer(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		BeanUtils.copyProperties(customer, customerDTO);
		return customerDTO;
	}
	@Override
	public Customer fromCustomerDTO(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDTO, customer);
		return customer;
	}
	@Override
	public SavingAccountDTO fromSavingAccount(SavingAccount savingAccount) {
		SavingAccountDTO savingAccountDTO = new SavingAccountDTO();
		BeanUtils.copyProperties(savingAccount, savingAccountDTO);
		savingAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));
		savingAccountDTO.setType(savingAccount.getClass().getSimpleName());
		return savingAccountDTO;
	}
	@Override
	public SavingAccount fromSavingAccountDTO(SavingAccountDTO savingAccountDTO) {
		SavingAccount savingAccount = new SavingAccount();
		BeanUtils.copyProperties(savingAccountDTO, savingAccount);
		savingAccount.setCustomer(fromCustomerDTO(savingAccountDTO.getCustomerDTO()));
		return savingAccount;
	}
	@Override
	public CurrentAccountDTO fromCurrentAccount(CurrentAccount currentAccount) {
		CurrentAccountDTO currentAccountDTO = new CurrentAccountDTO();
		BeanUtils.copyProperties(currentAccount, currentAccountDTO);
		currentAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
		currentAccountDTO.setType(currentAccount.getClass().getSimpleName());
		return currentAccountDTO;
	}
	@Override
	public CurrentAccount fromCurrentAccountDTO(CurrentAccountDTO currentAccountDTO) {
		CurrentAccount currentAccount = new CurrentAccount();
		BeanUtils.copyProperties(currentAccountDTO, currentAccount);
		currentAccount.setCustomer(fromCustomerDTO(currentAccountDTO.getCustomerDTO()));
		return currentAccount;
	}
	@Override
	public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation) {
		AccountOperationDTO accountOperationDTO = new AccountOperationDTO();
		BeanUtils.copyProperties(accountOperation, accountOperationDTO);
		accountOperationDTO.setBankAccountDTO(fromBankAccount(accountOperation.getBankAccount()));
		return accountOperationDTO;
	}
	@Override
	public AccountOperation fromAccountOperationDTO(AccountOperationDTO accountOperationDTO) {
		AccountOperation accountOperation = new AccountOperation();
		BeanUtils.copyProperties(accountOperationDTO, accountOperation);
		return accountOperation;
	}
	@Override
	public BankAccount fromBankAccountDTO(BankAccountDTO bankAccountDTO) {
		BankAccount bankAccount = new BankAccount() {
		};
		if(bankAccountDTO.getType().equals("CurrentAccount"))
		BeanUtils.copyProperties(bankAccountDTO, bankAccount,"interestRate");
		else 
			BeanUtils.copyProperties(bankAccountDTO, bankAccount,"overDraft");
		return bankAccount;
	}
	@Override
	public BankAccountDTO fromBankAccount(BankAccount bankAccount) {
		BankAccountDTO bankAccountDTO = new BankAccountDTO();
		BeanUtils.copyProperties(bankAccount, bankAccountDTO);
		return bankAccountDTO;
	}
	
}
