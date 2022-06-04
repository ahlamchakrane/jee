package ma.enset.ProjetJEE.web;
import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
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
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@GetMapping(path = "/accountOperations/{id}")
	public AccountOperationDTO getAccountOperation(@PathVariable(name = "id") Long id) {
		return accountOperationService.getAccountOperationDTO(id);
	}
	//recuperer la liste des account op d'un bankAccount
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') ")
	@GetMapping(path = "/bankAccount/accountOperations/{id}")
	public List<AccountOperationDTO> getAccountOperations(@PathVariable(name = "id") Long id) {
		return accountOperationService.listBankAccountOperations(id);
	}
	/*@GetMapping(path = "/accountOperations")
	public List<AccountOperationDTO> getAccountOperations() {
		return accountOperationService.listAccountOperationDTO();
	}*/
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@PutMapping(path = "/transfert/{idSource}")
	public void creditBankAccount(@PathVariable(name = "idSource") Long id,@RequestBody AccountOperationDTO accountOperationDTO) throws BankAccountNotFoundException, BalanceNotSufficientEception {
		Long idDestination = (long) Integer.parseInt(accountOperationDTO.getDescription());
		double amount = accountOperationDTO.getAmount();
		accountOperationService.transfer(id, idDestination, amount);
	}
	@PostAuthorize("hasAuthority('ADMIN')")
	@PutMapping(path = "/accountOperations/{id}")
	public AccountOperationDTO updateAccountOperation(@RequestBody AccountOperationDTO accountOperationDTO, @PathVariable Long id) {
		accountOperationDTO.setId(id);
		return accountOperationService.updateAccountOperationDTO(accountOperationDTO);
	}
	@PostAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping(path = "/accountOperations/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		accountOperationService.deleteAccountOperation(id);
	}
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@GetMapping(path = "/accountOperations/search")
	public List<AccountOperationDTO> searchAccountOperations(@RequestParam(name = "keyword", defaultValue ="") String keyword){
		return accountOperationService.searchAccountOperations("%"+keyword+"%");
	}
	
}
