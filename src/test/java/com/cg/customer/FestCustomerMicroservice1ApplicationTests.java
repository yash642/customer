package com.cg.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.customer.entity.Customer;
import com.cg.customer.exception.NullCustomerException;
import com.cg.customer.service.CustomerService;


@SpringBootTest
class FestCustomerMicroserviceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void findAllCustomer() {
		List<Customer> cust=new ArrayList<>();
		Customer cust1=new Customer("Deepali",70,"9876565433","deep@gmail.com");
		
		
		cust.add(cust1);
		
		
		CustomerService custService=mock(CustomerService.class);
		when(custService.listCustomer()).thenReturn(cust);
		List<Customer> custlist =custService.listCustomer();
		assertNotNull(custlist);
		assertFalse(custlist.isEmpty());
	
	}
	@Test
	void addCustomerTest() throws NullCustomerException {
		Customer cust=new Customer("Deepali",70,"9876565433","deep@gmail.com");
		CustomerService custService=mock(CustomerService.class);
		when(custService.addCustomer(cust)).thenReturn(cust);
		Customer cust2=custService.addCustomer(cust);
		assertEquals(cust,cust2);
		
		
		
		
	}

}
