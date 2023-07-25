package com.ust.customer.jpa.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ust.customer.jpa.exception.CustomerNotFoundException;
import com.ust.customer.jpa.exception.DuplicateFoundException;
import com.ust.customer.jpa.model.Customer;
import com.ust.customer.jpa.model.UserType;



@SpringBootTest
class CustomerServiceImplTest {
	
	@Autowired
	private CustomerService service;
	
//	@BeforeEach
//	public void setUp() {
//		
//		Customer c2=new Customer(101,"ansons","ansons@gmail.com",
//				LocalDate.of(2000, 10, 10),UserType.GENERAL);
//		Customer c3=new Customer(102,"anso","anso@gmail.com",
//				LocalDate.of(2001, 01, 12),UserType.PREMIUM);
//		Customer c4=new Customer(103,"ans","ans@gmail.com",
//				LocalDate.of(2002, 11, 20),UserType.GENERAL);
//		
//		service.addCustomer(c2);
//		service.addCustomer(c3);
//		service.addCustomer(c4);
//		
//		
//	}
//	
//	@AfterEach
//	public void tearDown() {
//		service.deleteCustomer(101);
//		service.deleteCustomer(102);
//		service.deleteCustomer(103);
//	}

	@Test
	void testAddCustomer() {
		Customer c1=new Customer(100,"anson","ansona@gmail.com",
				LocalDate.of(2000, 10, 10),UserType.GENERAL);
//		when
		Customer savedCustomer=service.addCustomer(c1);
//		service.addCustomer(c1);
//		then
		assertEquals(c1, savedCustomer);
		assertThrows(DuplicateFoundException.class, ()->service.addCustomer(c1));
	}
	
	

	@Test
	void testGetCustomer() {
		
//		Given
		int id=101;
		int id2=106;
		
//		when
		Customer c1=service.getCustomer(id);
		
		//then
		assertNotNull(c1);
		assertThrows(CustomerNotFoundException.class, ()->service.getCustomer(id2));
	}

	@Test
	void testUpdateCustomer() {
		Customer c6=new Customer(101,"a","a@gmail.com",
				LocalDate.of(2000, 10, 10),UserType.GENERAL);
		Customer update=service.updateCustomer(c6);
		assertEquals(c6, update);
	}

	@Test
	void testDeleteCustomer() {
			int id1=100;
			
			assertDoesNotThrow(()->service.deleteCustomer(id1));
			assertThrows(CustomerNotFoundException.class,()->service.deleteCustomer(id1));
	}

	@Test
	void testGetAllCustomer() {
		  List<Customer> list = service.getAllCustomer();
	        assertNotEquals(0, list.size());
	}

	@Test
	void testGetCustomerByName() {
//		Given
		String name="ansonalosious";
		String name2="ansooo";
		
//		when
		Customer c7=service.getCustomerByName(name);
		
		//then
		assertNotNull(c7);
		assertThrows(CustomerNotFoundException.class, ()->service.getCustomerByName(name2));
	}
//
	@Test
	void testFindByEmail() {
		String email1="ansona@gmail.com";
		String email2="ansonalos@gmail.com";
		Customer c2=service.getByEmail(email1);
		assertNotNull(c2);
		assertEquals(c2, service.getByEmail(email1));
		assertThrows(CustomerNotFoundException.class,()->service.getByEmail(email2));
		
	}

	@Test
	void testFindByInDobRange() {
		LocalDate from=LocalDate.of(1999, 01, 01);
        LocalDate to=LocalDate.of(2009, 01, 01);
        List<Customer> list=service.findByInDobRange(from, to);
        assertEquals(0, list.size());
	}

	@Test
	void testGetIdInRange() {
		int s=100;
		int e=200;
		List<Customer> list=service.getIdInRange(s, e);
		assertNotEquals(0, list.size());
	}

}
