package ma.enset.ProjetJEE;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ma.enset.ProjetJEE.dtos.BankAccountDTO;
import ma.enset.ProjetJEE.dtos.CustomerDTO;
import ma.enset.ProjetJEE.entities.AccountOperation;
import ma.enset.ProjetJEE.entities.CurrentAccount;
import ma.enset.ProjetJEE.entities.Customer;
import ma.enset.ProjetJEE.entities.SavingAccount;
import ma.enset.ProjetJEE.enums.AccountStatus;
import ma.enset.ProjetJEE.enums.OperationType;
import ma.enset.ProjetJEE.repositories.AccountOperationRepository;
import ma.enset.ProjetJEE.repositories.BankAccountRepository;
import ma.enset.ProjetJEE.repositories.CustomerRepository;
import ma.enset.ProjetJEE.services.bankAccount.BankAccountService;
import ma.enset.ProjetJEE.services.customer.CustomerService;
import ma.enset.ProjetJEE.services.roleService.RoleService;
import ma.enset.ProjetJEE.services.userService.UserService;


@SpringBootApplication
public class ProjetJEEApplication {
	public static void main(String[] args) {
		SpringApplication.run( ProjetJEEApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
		
	@Bean
	public CommandLineRunner start(BankAccountService bankService, CustomerService customerService) {
		return args -> {
			Stream.of("Hassan","imane","Mohamed").forEach(name->{
				CustomerDTO customer = new CustomerDTO();
				customer.setEmail(name+"@gmail.com");
				customer.setName(name);
				customerService.saveCustomerDTO(customer);	
			});
			
			List<BankAccountDTO> bankAccounts= bankService.bankAccountListDTO();
//			for(BankAccountDTO bankAccount : bankAccounts) {
//				for(int i=0;i<5;i++) {
//					bankService.credit(bankAccount.getId(), 10000+Math.random()*12000,"Credit "+i);
//					bankService.debit(bankAccount.getId(), 100+Math.random()*9000,"Credit "+i);
//				}
//			}
		};
	}
	@Bean
	public CommandLineRunner start(CustomerRepository customerRepository,
								   BankAccountRepository bankAccountRepository,
								   AccountOperationRepository accountOperationRepository) {
		return args -> {
			Stream.of("Hassan","Yassine","Aicha").forEach(name->{
				Customer customer = new Customer();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				customerRepository.save(customer);
			});
			customerRepository.findAll().forEach(cat->{
				CurrentAccount currentAccount = new CurrentAccount();
				currentAccount.setId(UUID.randomUUID().toString());
				currentAccount.setBalance(Math.random()*9000);
				currentAccount.setCreatedAt(new Date());
				currentAccount.setStatus(AccountStatus.CREATED);
				currentAccount.setCustomer(cat);
				currentAccount.setOverDraft(9000);
				bankAccountRepository.save(currentAccount);
				
				SavingAccount savingAccount = new SavingAccount();
				savingAccount.setId(UUID.randomUUID().toString());
				savingAccount.setBalance(Math.random()*9000);
				savingAccount.setCreatedAt(new Date());
				savingAccount.setStatus(AccountStatus.CREATED);
				savingAccount.setCustomer(cat);
				savingAccount.setInterestRate(5.5);
				bankAccountRepository.save(savingAccount);
				
			
			});
			bankAccountRepository.findAll().forEach(acc->{
				for(int i=0;i<5;i++) {
					AccountOperation accountOperation = new AccountOperation();
					accountOperation.setOperationDate(new Date());
					accountOperation.setAmount(Math.random()*12000);
					accountOperation.setType(Math.random()>0.5? OperationType.DEBIT: OperationType.CREDIT);
					accountOperation.setBankAccount(acc);
					accountOperationRepository.save(accountOperation);
				}
			});
		};
	}
	@Bean
	CommandLineRunner saveUsers(UserService userService, RoleService roleService) {
		return args -> {
			userService.saveUser("fatima","12","12");
			userService.saveUser("hassan", "12", "12");
			
			roleService.saveRole("USER", "role user");
			roleService.saveRole("ADMIN", "role admin");
			
			roleService.addRoleToUser("fatima", "USER");
			roleService.addRoleToUser("fatima", "ADMIN");
			roleService.addRoleToUser("hassan", "USER");
			};
	}
}
