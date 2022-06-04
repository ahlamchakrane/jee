package ma.enset.ProjetJEE.web;
import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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
import ma.enset.ProjetJEE.dtos.CustomerDTO;
import ma.enset.ProjetJEE.exceptions.CustomerNotFoundException;
import ma.enset.ProjetJEE.services.customer.CustomerService;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {
	private CustomerService customerService;
	
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@GetMapping("/customers")
	public List<CustomerDTO> cutomerList(){
		return customerService.listCustomersDTO();
	}
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @GetMapping("/customers/{id}")
	public CustomerDTO getCustomers(@PathVariable(name = "id") Long id) throws CustomerNotFoundException{
		return customerService.getCustomerDTO(id);
	}
	
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@GetMapping("/customers/search")
	public List<CustomerDTO> searchCustomers(@RequestParam(name = "keyword", defaultValue ="") String keyword){
		return customerService.searchCustomers("%"+keyword+"%");
	}
	@PostAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/customers")
	public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
		return customerService.saveCustomerDTO(customerDTO);
	}
	
	@PostAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/customers/{id}")
	public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long id) {
		customerDTO.setId(id);
		return customerService.updateCustomerDTO(customerDTO);
	}
	@PostAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/customers/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
	}
}
