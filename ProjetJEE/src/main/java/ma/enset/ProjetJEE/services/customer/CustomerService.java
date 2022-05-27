package ma.enset.ProjetJEE.services.customer;

import java.util.List;

import ma.enset.ProjetJEE.dtos.CustomerDTO;
import ma.enset.ProjetJEE.exceptions.CustomerNotFoundException;

public interface CustomerService {
	CustomerDTO saveCustomerDTO(CustomerDTO customerDTO);
	CustomerDTO updateCustomerDTO(CustomerDTO customerDTO);
	void deleteCustomer(Long customerId);
	List<CustomerDTO> listCustomersDTO();
	CustomerDTO getCustomerDTO(Long customerid) throws CustomerNotFoundException;
	List<CustomerDTO> searchCustomers(String keyword);
}
