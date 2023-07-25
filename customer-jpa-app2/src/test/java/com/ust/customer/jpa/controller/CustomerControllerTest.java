package com.ust.customer.jpa.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ust.customer.jpa.exception.DuplicateFoundException;
import com.ust.customer.jpa.model.Customer;
import com.ust.customer.jpa.model.UserType;
import com.ust.customer.jpa.service.CustomerService;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(value=CustomerController.class)
class CustomerControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	CustomerService service;
	



	@Test
	void testGetCustomer() throws Exception {
		
		//given
		Customer c1=new Customer(101,"anson","anson@gmail.com",
				LocalDate.of(2000, 10, 10),UserType.GENERAL);
		
		int id=c1.getId();
		
		//when
		when(service.getCustomer(id)).thenReturn(c1);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/"+id))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id",Matchers.is(101)))
		.andExpect(jsonPath("$.customerName",Matchers.is("anson")))
		.andExpect(jsonPath("$.email",Matchers.is("anson@gmail.com")))
		.andExpect(jsonPath("$.type",Matchers.is("GENERAL")))
		.andExpect(jsonPath("$.dob",Matchers.is("2000-10-10")));
	}
	
	
	@Test
	void testAddCustomer() throws Exception{
		Customer c1=new Customer(101,"anson","anson@gmail.com",
				LocalDate.of(2000, 10, 10),UserType.GENERAL);

		when(service.addCustomer(c1)).thenReturn(c1);
		
		mockMvc.perform(post("/api/customer")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(asJsonString(c1)))
		       .andExpect(status().isCreated())
		       .andExpect(jsonPath("$.id",Matchers.is(101)))
		       .andExpect(jsonPath("$.customerName",Matchers.is("anson")))
		       .andExpect(jsonPath("$.email",Matchers.is("anson@gmail.com")))
		       .andExpect(jsonPath("$.type",Matchers.is("GENERAL")))
		       .andExpect(jsonPath("$.dob",Matchers.is("2000-10-10")));
	}
//
//	@Test
//	void testGetAllCustomers() {
//		fail("Not yet implemented");
//	}

//	@Test
//	void testGetCustomerByname() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testUpdateCustomer() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeleteCustomer() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByEmail() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSearchCustomerByDobRange() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSearchCustomerByIdRange() {
//		fail("Not yet implemented");
//	}

	
	private String asJsonString(Object object) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(object);
    }

}
