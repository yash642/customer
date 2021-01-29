package com.cg.customer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.cg.customer.entity.Customer;
import com.cg.customer.exception.CustomerNotFoundException;
import com.cg.customer.exception.NullCustomerException;
import com.cg.customer.service.CustomerService;


@RestController
@RequestMapping("/CustomerCtrl")
@CrossOrigin(origins = "*")
public class CustomerController {

	@Autowired
	CustomerService custService;

	@PostMapping("/addcust")
	public Customer addCustomer(@RequestBody Customer cust) throws NullCustomerException {
		return custService.addCustomer(cust);

	}

	@GetMapping("/getcust/{id}")
	public Optional<Customer> SearchCustomerById(@PathVariable Integer id) throws CustomerNotFoundException {
		return custService.SearchCustomer(id);

	}

	@GetMapping("/getall")
	public List<Customer> listAllCustomer() {
		return custService.listCustomer();

	}

}
