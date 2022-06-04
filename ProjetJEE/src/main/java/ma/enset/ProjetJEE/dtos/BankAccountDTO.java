package ma.enset.ProjetJEE.dtos;

import java.util.Date;
import java.util.List;

import lombok.Data;
import ma.enset.ProjetJEE.enums.AccountStatus;

@Data
public class BankAccountDTO {
	private Long id;
	private String type;
	private double balance;
	private  Date createdAt;
	private AccountStatus status;
	private String description;
	// for saving account
	private double interestRate;
	// for current account
	private double overDraft;
	private CustomerDTO customerDTO;
	private List<AccountOperationDTO> accountOperationsDTOs;
}
