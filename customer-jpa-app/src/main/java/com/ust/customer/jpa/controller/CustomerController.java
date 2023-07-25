package com.ust.customer.jpa.controller;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ust.customer.jpa.model.Customer;
import com.ust.customer.jpa.service.CustomerService;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	@Autowired
	private CustomerService service;
	@ResponseStatus(code=HttpStatus.CREATED)
	@PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
	return service.addCustomer(customer);
	}
	@ResponseStatus(code=HttpStatus.OK)
    @GetMapping
    public List<Customer> getAllCustomers(){
	return service.getAllCustomer();
}
    
    @GetMapping("/{id}")
	public Customer getCustomer(@PathVariable int id) {
		return service.getCustomer(id);
	}
    
    @GetMapping("/search")
    public Customer getCustomerByname(@RequestParam("name") String name) {
    	return service.getCustomerByName(name);
    }
    
    @PutMapping
    @ResponseStatus(code=HttpStatus.ACCEPTED)
    public Customer updateCustomer(@RequestBody Customer customer) {
    	return service.updateCustomer(customer);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.OK)
    public void deleteCustomer(@PathVariable int id) {
    	service.deleteCustomer(id);
    }
    
    @GetMapping("/search/{email}")
    public Customer findByEmail(@PathVariable String email) {
    	return service.getByEmail(email);
    }
    
//    @GetMapping("/dob/from/{from}/to/{to}")
//    public List<Customer> searchCustomerByDobRange(@PathVariable String from,@PathVariable String to){
//    	
//    	return service.findByInDobRange(LocalDate.parse(from), LocalDate.parse(to));
//    	
//    }
    
    @GetMapping("/dob/from/{from}/to/{to}")
    public List<Customer> searchCustomerByDobRange(@PathVariable @DateTimeFormat(iso=ISO.DATE) LocalDate from,@PathVariable @DateTimeFormat(iso=ISO.DATE) LocalDate to){
    	
    	return service.findByInDobRange(from, to);
    	
    }
    
    @GetMapping("/id/start/{start}/end/{end}")
    public List<Customer> searchCustomerByIdRange(@PathVariable int start,@PathVariable int end){
    	return service.getIdInRange(start, end);
    }

}