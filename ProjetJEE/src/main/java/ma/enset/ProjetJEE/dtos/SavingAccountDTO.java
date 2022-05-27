package ma.enset.ProjetJEE.dtos;

import java.util.Date;

import lombok.Data;
import ma.enset.ProjetJEE.enums.AccountStatus;

@Data
public class SavingAccountDTO extends BankAccountDTO{
	private String id;
	private double balance;
	private  Date createdAt;
	private AccountStatus status;
	private CustomerDTO customerDTO;
	private double interestRate; //taux d'interet

}
