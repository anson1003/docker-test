package com.ust.customer.jpa.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ust.customer.jpa.model.Customer;



public interface CustomerRepo extends JpaRepository<Customer, Integer>{
	
public Optional<Customer> findByCustomerName(String customerName);
public Optional<Customer> findByEmail(String email);

@Query(value="from Customer where dob between:from and :to")
public List<Customer> findCustomerInDobRange(LocalDate from,LocalDate to);

@Query(value="select * from customer_data where id between :start and :end",nativeQuery=true)
public List<Customer> findIdInRange(int start,int end);

}
