package com.ust.customer.jpa.service;

import java.time.LocalDate;
import java.util.List;

import com.ust.customer.jpa.model.Customer;


public interface CustomerService {
	
	public Customer addCustomer(Customer customer);
	public Customer getCustomer(int id);
	public Customer updateCustomer(Customer customer);
	public void deleteCustomer(int id);
	public List<Customer> getAllCustomer();
	public Customer getCustomerByName(String name);
	public Customer getByEmail(String email);
	public List<Customer> findByInDobRange(LocalDate from, LocalDate to);
	public List<Customer> getIdInRange(int start,int end);
}
