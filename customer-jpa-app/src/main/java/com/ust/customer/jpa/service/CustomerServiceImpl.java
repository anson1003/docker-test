package com.ust.customer.jpa.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.customer.jpa.exception.CustomerNotFoundException;
import com.ust.customer.jpa.exception.DuplicateFoundException;
import com.ust.customer.jpa.model.Customer;
import com.ust.customer.jpa.repository.CustomerRepo;
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepo repo;
	
	


	public CustomerRepo getRepo() {
		return repo;
	}


	public void setRepo(CustomerRepo repo) {
		this.repo = repo;
	}


	public Customer addCustomer(Customer customer) {
		
		for(Customer c:repo.findAll()) {
			if(c.getId()==customer.getId()) {
				throw new DuplicateFoundException("Duplicate customer found");
			}
		}
		return repo.save(customer);
	}


	public Customer getCustomer(int id) {
//		if(!repo.existsById(id)) {
//			throw new CustomerNotFoundException("Customer with ID : "+id+" Not found");
//		}

	Customer customer=repo.findById(id).orElseThrow(()->new CustomerNotFoundException("Customer with ID :"+id+"not found"));
//Customer customer = repo.findById(id).get();
		
		return customer;

	}


	public Customer updateCustomer(Customer customer) {
		int id=customer.getId();
		if(!repo.existsById(id)) {
			throw new CustomerNotFoundException("Customer with ID : "+id+" Not found");
		}
		
//		Customer cust=repo.findById(customer.getId()).orElseThrow(()->new CustomerNotFoundException("Customer "+customer.getId()+" Not Found"));
		return repo.save(customer);
	}


	public void deleteCustomer(int id) {
		if(!repo.existsById(id)) {
			throw new CustomerNotFoundException("Customer with ID : "+id+" Not found");
		}
		repo.deleteById(id);
	}


	public List<Customer> getAllCustomer() {
		return repo.findAll();
	}


	public Customer getCustomerByName(String name) {
		Customer customer=repo.findByCustomerName(name).orElseThrow(()->new CustomerNotFoundException("Customer with name-"+name+" Not Found"));
		return customer;
	}

	public Customer getByEmail(String mail) {
		Customer customer=repo.findByEmail(mail).orElseThrow(()->new CustomerNotFoundException("Customer with email-"+mail+" Not Found"));
		return customer;
	}
	@Override
	public List<Customer> findByInDobRange(LocalDate from, LocalDate to) {
		// TODO Auto-generated method stub
		return repo.findCustomerInDobRange(from,to);
	}


	@Override
	public List<Customer> getIdInRange(int start, int end) {
		return repo.findIdInRange(start, end);
	}
	
}
