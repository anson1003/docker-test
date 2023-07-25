package com.ust.customer.jpa.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ust.customer.jpa.exception.CustomerNotFoundException;
import com.ust.customer.jpa.exception.DuplicateFoundException;
import com.ust.customer.jpa.model.Customer;
import com.ust.customer.jpa.model.UserType;
import com.ust.customer.jpa.repository.CustomerRepo;


@ExtendWith(MockitoExtension.class)
class CustomerServiceImplUnitTest {
	
	@Mock
	private CustomerRepo repo;
	
	@InjectMocks
	private CustomerServiceImpl service;
	

	@Test
	void testAddCustomer() {
		Customer c1=new Customer(100,"anson","ansona@gmail.com",
				LocalDate.of(2000, 10, 10),UserType.GENERAL);
		Customer c2=new Customer(109,"reji","reji@gmail.com",
				LocalDate.of(2000, 10, 10),UserType.GENERAL);
//		when
		when(repo.save(c1)).thenReturn(c1);
		when(repo.save(c2)).thenThrow(new DuplicateFoundException("Duplicate Customer"));
		Customer savedCustomer=service.addCustomer(c1);

//		then
		assertEquals(c1, savedCustomer);
		assertThrows(DuplicateFoundException.class, ()->service.addCustomer(c2));
		
		verify(repo,times(1)).save(c1);
		verify(repo,times(1)).save(c2);
	}
	
	
//
	@Test
	void testGetCustomer() {
		
//		Given
		int id1=100;
		int id2=101;
		Customer c1=new Customer(100,"anson","ansona@gmail.com",
				LocalDate.of(2000, 10, 10),UserType.GENERAL);
		
//		when
		when(repo.findById(id1)).thenReturn(Optional.of(c1));
		when(repo.findById(id2)).thenThrow(CustomerNotFoundException.class);
		
		//then
		assertEquals(c1, service.getCustomer(id1));
		assertThrows(CustomerNotFoundException.class, ()->service.getCustomer(id2));
		
		verify(repo,times(1)).findById(id1);
		verify(repo,times(1)).findById(id2);
	}
//
	@Test
	void testUpdateCustomer() {
		Customer c6=new Customer(101,"a","a@gmail.com",
				LocalDate.of(2000, 10, 10),UserType.GENERAL);
		Customer c7=new Customer(108,"a","a@gmail.com",
				LocalDate.of(2000, 10, 10),UserType.GENERAL);
		
		
//		service.addCustomer(c6);
		when(repo.existsById(101)).thenReturn(true);
		when(repo.save(c6)).thenReturn(c6);
		when(repo.existsById(108)).thenReturn(false);
//		when(repo.save(c7)).thenThrow(CustomerNotFoundException.class);
		
		Customer updated=service.updateCustomer(c6);
		
		assertEquals(c6, updated);
		assertThrows(CustomerNotFoundException.class, ()->service.updateCustomer(c7));
		
		
		verify(repo,times(1)).existsById(101);
		verify(repo,times(1)).save(c6);
		
		verify(repo,times(1)).existsById(108);
//		verify(repo,times(1)).save(c7);
	}
//
	@Test
	void testDeleteCustomer() {
		

		int id1=101;
		int  id2=111;
		
		when(repo.existsById(id1)).thenReturn(true);
		when(repo.existsById(id2)).thenReturn(false);
		
		assertDoesNotThrow(()->service.deleteCustomer(id1));
		assertThrows(CustomerNotFoundException.class,()->service.deleteCustomer(id2));
		
		verify(repo,times(1)).deleteById(id1);
		verify(repo,times(1)).existsById(id2);
		
	}
//
//	@Test
//	void testGetAllCustomer() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetCustomerByName() {
////		Given
//		String name="anson";
//		String name2="ansooo";
//		
////		when
//		Customer c7=service.getCustomerByName(name);
//		
//		//then
//		assertNotNull(c7);
//		assertThrows(CustomerNotFoundException.class, ()->service.getCustomerByName(name2));
//	}
//
//	@Test
//	void testFindByEmail() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByInDobRange() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetIdInRange() {
//		fail("Not yet implemented");
//	}

}
