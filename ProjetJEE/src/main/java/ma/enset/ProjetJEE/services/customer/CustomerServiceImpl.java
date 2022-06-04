package ma.enset.ProjetJEE.services.customer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import ma.enset.ProjetJEE.dtos.CustomerDTO;
import ma.enset.ProjetJEE.entities.Customer;
import ma.enset.ProjetJEE.exceptions.CustomerNotFoundException;
import ma.enset.ProjetJEE.mappers.BankAccountMappersService;
import ma.enset.ProjetJEE.repositories.CustomerRepository;
@Service
@Transactional 
@Slf4j //pour loguer les messages
//log4j pour al journalisation 
//slf4j egalement 
public class CustomerServiceImpl implements CustomerService {
	private CustomerRepository customerRepository;
	private BankAccountMappersService bankAccountMappersService;
	
	public CustomerServiceImpl(CustomerRepository customerRepository, BankAccountMappersService bankAccountMappersService) {
		this.customerRepository = customerRepository;
		this.bankAccountMappersService = bankAccountMappersService;
	}	

	@Override
	public CustomerDTO saveCustomerDTO(CustomerDTO customerDTO) {
		Customer customer = bankAccountMappersService.fromCustomerDTO(customerDTO);
		Customer savedCustomer = customerRepository.save(customer);
		return bankAccountMappersService.fromCustomer(savedCustomer);
	}
	@Override
	public CustomerDTO updateCustomerDTO(CustomerDTO customerDTO) {
		log.info("Updating Customer");
		Customer customer = bankAccountMappersService.fromCustomerDTO(customerDTO);
		Customer savedCustomer = customerRepository.save(customer);
		return bankAccountMappersService.fromCustomer(savedCustomer);
	}
	@Override
	public void deleteCustomer(Long customerId) {
		customerRepository.deleteById(customerId);
	}

	@Override
	public List<CustomerDTO> listCustomersDTO() {
		List<Customer> customers = customerRepository.findAll();
		List<CustomerDTO> customersDTO = customers.stream().map(customer -> bankAccountMappersService.fromCustomer(customer)).collect(Collectors.toList());
		return customersDTO;
	}
	@Override
	public CustomerDTO getCustomerDTO(Long customerid) throws CustomerNotFoundException {
		Customer customer = customerRepository.findById(customerid)
		.orElseThrow(()-> new CustomerNotFoundException("Customer not found"));
		return bankAccountMappersService.fromCustomer(customer);
	}

	@Override
	public List<CustomerDTO> searchCustomers(String keyword) {
		List<Customer> customers = customerRepository.searchCustomer(keyword);
		List<CustomerDTO> customerDTOs= customers.stream().map(cust->bankAccountMappersService.fromCustomer(cust)).collect(Collectors.toList());
		return customerDTOs;
	}
}
