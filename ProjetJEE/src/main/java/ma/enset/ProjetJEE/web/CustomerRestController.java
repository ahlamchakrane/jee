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
import ma.enset.ProjetJEE.dtos.CustomerDTO;
import ma.enset.ProjetJEE.exceptions.CustomerNotFoundException;
import ma.enset.ProjetJEE.services.customer.CustomerService;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {
	private CustomerService customerService;
	@GetMapping(path= "/user/customers")
	public List<CustomerDTO> cutomerList(){
		return customerService.listCustomersDTO();
	}
	@GetMapping(path = "/user/customers/{id}")
	public CustomerDTO getCustomers(@PathVariable(name = "id") Long id) throws CustomerNotFoundException{
		return customerService.getCustomerDTO(id);
	}
	@GetMapping(path = "/user/customers/search")
	public List<CustomerDTO> searchCustomers(@RequestParam(name = "keyword", defaultValue ="") String keyword){
		return customerService.searchCustomers("%"+keyword+"%");
	}
	@PostMapping(path = "/admin/customers")
	public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
		return customerService.saveCustomerDTO(customerDTO);
	}
	
	@PutMapping(path = "/user/customers/{id}")
	public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long id) {
		customerDTO.setId(id);
		return customerService.updateCustomerDTO(customerDTO);
	}
	@DeleteMapping(path = "/admin/customers/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
	}
}
