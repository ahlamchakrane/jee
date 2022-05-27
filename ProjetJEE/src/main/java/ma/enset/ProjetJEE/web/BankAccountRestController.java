package ma.enset.ProjetJEE.web;

import java.util.List;

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
	
	@GetMapping(path= "/user/bankAccounts")
	public List<BankAccountDTO> bankAccountsList(){
		return bankAccountService.bankAccountListDTO();
	}
	@GetMapping(path = "/user/bankAccounts/{id}")
	public BankAccountDTO getBankAccount(@PathVariable(name = "id") String id) throws BankAccountNotFoundException{
		return bankAccountService.getBankAccountDTO(id);
	}
	//write
	@PostMapping(path = "/admin/bankAccounts")
	public BankAccountDTO saveBankAccount(@RequestBody BankAccountDTO bankAccountDTO) throws CustomerNotFoundException {
		if(bankAccountDTO.getType().equals("CurrentAccount") && bankAccountDTO.getOverDraft()!=0) {
			bankAccountService.saveCurrentBankAccount(bankAccountDTO);
		} else if(bankAccountDTO.getType().equals("SavingAccount") && bankAccountDTO.getInterestRate() !=0) {
			bankAccountService.saveSavingBankAccount(bankAccountDTO);
		}
		return null;
	}
	@PutMapping(path = "/admin/bankAccounts/{id}")
	public void updateBankAccount(@RequestBody BankAccountDTO bankAccountDTO, @PathVariable String id) {
		System.out.println(bankAccountDTO);
		bankAccountDTO.setId(id);
		bankAccountService.updateBankAccountDTO(bankAccountDTO);		
	}
	@DeleteMapping(path = "/admin/bankAccounts/{id}")
	public void deleteBankAccount(@PathVariable String id) {
		bankAccountService.deleteBankAccount(id);
	}
	@GetMapping(path = "/user/bankAccounts/search")
	public List<BankAccountDTO> searchBankAccounts(@RequestParam(name = "keyword", defaultValue ="") String keyword){
		return bankAccountService.searchBankAccounts("%"+keyword+"%");
	}
	@GetMapping(path = "/user/accounts/{accountId}/pageOperations")
	public AccountHistoryDTO getAccountHistory(@PathVariable String accountId,@RequestParam(name="page", defaultValue = "0") int page,@RequestParam(name="size", defaultValue = "8") int size) throws BankAccountNotFoundException{
		return bankAccountService.getAccountHistory(accountId, page, size);
	}
}
