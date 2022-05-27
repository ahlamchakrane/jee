package ma.enset.ProjetJEE.dtos;

import java.util.List;

import lombok.Data;
@Data
public class AccountHistoryDTO {
	private String accountId;
	private double bakance;
	private int currentPage;
	private int totalPages;
	private int pageSize;
	private List<AccountOperationDTO> accountOperationDTOs;

}
