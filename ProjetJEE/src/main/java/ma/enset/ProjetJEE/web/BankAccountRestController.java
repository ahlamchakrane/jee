package ma.enset.ProjetJEE.web;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.ProjetJEE.dtos.AccountHistoryDTO;
import ma.enset.ProjetJEE.dtos.AccountOperationDTO;
import ma.enset.ProjetJEE.dtos.BankAccountDTO;
import ma.enset.ProjetJEE.exceptions.BankAccountNotFoundException;
import ma.enset.ProjetJEE.exceptions.CustomerNotFoundException;
import ma.enset.ProjetJEE.services.bankAccount.BankAccountService;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class BankAccountRestController {
	private BankAccountService bankAccountService;
	
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@GetMapping(path= "/bankAccounts")
	public List<BankAccountDTO> bankAccountsList(){
		return bankAccountService.bankAccountListDTO();
	}
	//recuperer la liste des account op d'un bankAccount
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER') ")
	@GetMapping(path = "/customer/{id}/bankAccounts")
	public List<BankAccountDTO> getCustomerBankAccounts(@PathVariable(name = "id") Long id) {
		return bankAccountService.listCustomerBankAccounts(id);
	}
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@GetMapping(path = "/bankAccounts/{id}")
	public BankAccountDTO getBankAccount(@PathVariable(name = "id") Long id) throws BankAccountNotFoundException{
		return bankAccountService.getBankAccountDTO(id);
	}
	@PostAuthorize("hasAuthority('ADMIN')")
	@PostMapping(path = "/bankAccounts")
	public BankAccountDTO saveBankAccount(@RequestBody BankAccountDTO bankAccountDTO) throws CustomerNotFoundException {
		if(bankAccountDTO.getType().equals("CurrentAccount") && bankAccountDTO.getOverDraft()!=0) {
			bankAccountService.saveCurrentBankAccount(bankAccountDTO);
		} else if(bankAccountDTO.getType().equals("SavingAccount") && bankAccountDTO.getInterestRate() !=0) {
			bankAccountService.saveSavingBankAccount(bankAccountDTO);
		}
		return null;
	}
	@PostAuthorize("hasAuthority('ADMIN')")
	@PutMapping(path = "/bankAccounts/{id}")
	public void updateBankAccount(@RequestBody BankAccountDTO bankAccountDTO, @PathVariable Long id) {
		bankAccountDTO.setId(id);
		bankAccountService.updateBankAccountDTO(bankAccountDTO);		
	}
	@PostAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping(path = "/bankAccounts/{id}")
	public void deleteBankAccount(@PathVariable Long id) {
		bankAccountService.deleteBankAccount(id);
	}
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@GetMapping(path = "/bankAccounts/search")
	public List<BankAccountDTO> searchBankAccounts(@RequestParam(name = "keyword", defaultValue ="") String keyword){
		return bankAccountService.searchBankAccounts("%"+keyword+"%");
	}
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@GetMapping(path = "/accounts/{accountId}/pageOperations")
	public AccountHistoryDTO getAccountHistory(@PathVariable Long accountId,@RequestParam(name="page", defaultValue = "0") int page,@RequestParam(name="size", defaultValue = "8") int size) throws BankAccountNotFoundException{
		return bankAccountService.getAccountHistory(accountId, page, size);
	}
}
