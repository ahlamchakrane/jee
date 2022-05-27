package ma.enset.ProjetJEE.web;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ProjetJEE.dtos.AccountOperationDTO;
import ma.enset.ProjetJEE.exceptions.BalanceNotSufficientEception;
import ma.enset.ProjetJEE.exceptions.BankAccountNotFoundException;
import ma.enset.ProjetJEE.services.accountOperation.AccountOperationService;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class AccountOperationRestController {
	private AccountOperationService accountOperationService;
	
	//retourne les information d'un account operation
	@GetMapping(path = "/user/accountOperations/{id}")
	public AccountOperationDTO getAccountOperation(@PathVariable(name = "id") Long id) {
		return accountOperationService.getAccountOperationDTO(id);
	}
	//recuperer la liste des account op d'un bankAccount
	@GetMapping(path = "/user/bankAccount/accountOperations/{id}")
	public List<AccountOperationDTO> getAccountOperations(@PathVariable(name = "id") String id) {
		return accountOperationService.listBankAccountOperations(id);
	}
	/*@GetMapping(path = "/accountOperations")
	public List<AccountOperationDTO> getAccountOperations() {
		return accountOperationService.listAccountOperationDTO();
	}*/
	@PutMapping(path = "/admin/transfert/{idSource}")
	public void creditBankAccount(@PathVariable(name = "idSource") String id,@RequestBody AccountOperationDTO accountOperationDTO) throws BankAccountNotFoundException, BalanceNotSufficientEception {
		String idDestination = accountOperationDTO.getDescription();
		double amount = accountOperationDTO.getAmount();
		accountOperationService.transfer(id, idDestination, amount);
	}
	@PutMapping(path = "/admin/accountOperations/{id}")
	public AccountOperationDTO updateAccountOperation(@RequestBody AccountOperationDTO accountOperationDTO, @PathVariable Long id) {
		accountOperationDTO.setId(id);
		return accountOperationService.updateAccountOperationDTO(accountOperationDTO);
	}
	@DeleteMapping(path = "/admin/accountOperations/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		accountOperationService.deleteAccountOperation(id);
	}
	@GetMapping(path = "/user/accountOperations/search")
	public List<AccountOperationDTO> searchAccountOperations(@RequestParam(name = "keyword", defaultValue ="") String keyword){
		return accountOperationService.searchAccountOperations("%"+keyword+"%");
	}
	
}
