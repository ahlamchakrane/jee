package ma.enset.ProjetJEE.dtos;

import java.util.Date;

import lombok.Data;
import ma.enset.ProjetJEE.enums.OperationType;
@Data
public class AccountOperationDTO {
	private Long id;
	private Date operationDate;
	private double amount;
	private OperationType type;
	private String description;
	private BankAccountDTO bankAccountDTO;
}
