package ma.enset.ProjetJEE.mappers;

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

public interface BankAccountMappersService {
	Customer fromCustomerDTO(CustomerDTO customerDTO);
	CustomerDTO fromCustomer(Customer customerO);
	SavingAccountDTO fromSavingAccount(SavingAccount savingAccount);
	SavingAccount fromSavingAccountDTO(SavingAccountDTO savingAccountDTO);
	CurrentAccountDTO fromCurrentAccount(CurrentAccount currentAccount);
	CurrentAccount fromCurrentAccountDTO(CurrentAccountDTO currentAccountDTO);
	AccountOperationDTO fromAccountOperation(AccountOperation accountOperation);
	AccountOperation fromAccountOperationDTO(AccountOperationDTO accountOperationDTO);
	BankAccountDTO fromBankAccount(BankAccount bankAccount);
	BankAccount fromBankAccountDTO(BankAccountDTO bankAccountDTO);
}
