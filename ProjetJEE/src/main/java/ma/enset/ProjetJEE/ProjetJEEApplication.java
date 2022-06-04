package ma.enset.ProjetJEE;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
import ma.enset.ProjetJEE.security.entities.AppRole;
import ma.enset.ProjetJEE.security.entities.AppUser;
import ma.enset.ProjetJEE.security.services.AccountService;
import ma.enset.ProjetJEE.services.bankAccount.BankAccountService;
import ma.enset.ProjetJEE.services.customer.CustomerService;

@SpringBootApplication
public class ProjetJEEApplication {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjetJEEApplication.class, args);
    }
    //@Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService, CustomerService customerService,AccountService accountService){
        return args -> {

            accountService.addNewRole(new AppRole(null, "ADMIN"));
            accountService.addNewRole(new AppRole(null, "USER"));

            accountService.addNewUser(new AppUser(null, "user1", "1234", new ArrayList<>()));
            accountService.addNewUser(new AppUser(null, "admin", "1234", new ArrayList<>()));
            accountService.addNewUser(new AppUser(null, "user2", "1234", new ArrayList<>()));
            accountService.addNewUser(new AppUser(null, "user3", "1234", new ArrayList<>()));

            accountService.addRoleToUser ("user1", "USER");
            accountService.addRoleToUser("admin","ADMIN");
            accountService.addRoleToUser("user2", "USER");
            accountService.addRoleToUser("user3", "USER");

           Stream.of("Hind","Ahlam","Taoufik").forEach(name->{
               CustomerDTO customer=new CustomerDTO();
               customer.setName(name);
               customer.setEmail(name+"@gmail.com");
               customerService.saveCustomerDTO(customer);
           });
        };
            
    }
    @Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository){
        return args -> {
            Stream.of("Hassan","Yassine","Aicha").forEach(name->{
                Customer customer=new Customer();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                customerRepository.save(customer);
            });
          /*  customerRepository.findAll().forEach(cust->{
                CurrentAccount currentAccount=new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*90000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(cust);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount=new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random()*90000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(cust);
                savingAccount.setInterestRate(5.5);
                bankAccountRepository.save(savingAccount);

            });
            bankAccountRepository.findAll().forEach(acc->{
                for (int i = 0; i <5 ; i++) {
                    AccountOperation accountOperation=new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random()*1000);
                    accountOperation.setType(Math.random()>0.5? OperationType.DEBIT: OperationType.CREDIT);
                    accountOperation.setBankAccount(acc);
                    accountOperationRepository.save(accountOperation);
                }

            });*/
        };

    }

}
