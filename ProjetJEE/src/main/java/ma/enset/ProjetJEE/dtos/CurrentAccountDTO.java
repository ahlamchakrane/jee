package ma.enset.ProjetJEE.dtos;

import java.util.Date;

import lombok.Data;
import ma.enset.ProjetJEE.enums.AccountStatus;
@Data
public class CurrentAccountDTO extends BankAccountDTO{
	private Long id;
	private double balance;
	private  Date createdAt;
	private AccountStatus status;
	private CustomerDTO customerDTO;
	private double overDraft; //decouvert

}
