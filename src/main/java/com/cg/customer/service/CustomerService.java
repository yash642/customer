package com.cg.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cg.customer.dao.CustomerDao;
import com.cg.customer.entity.Customer;
import com.cg.customer.exception.CustomerNotFoundException;
import com.cg.customer.exception.NullCustomerException;

/**
 * 
 * @author Shubham Sharma
 */
@Service
public class CustomerService {

	@Autowired
	CustomerDao customerDao;

	public Customer addCustomer(Customer cust) throws NullCustomerException {
		if(cust.getCustId() == null)
			throw new NullCustomerException("Invalid Value");
		return customerDao.save(cust);

	}

	public Optional<Customer> SearchCustomer(Integer id) throws CustomerNotFoundException {
		Optional<Customer> cust = customerDao.findById(id);
		if(cust == null)
			throw new CustomerNotFoundException("Customer Not Found with id : " + id);
		return cust;

	}

	public List<Customer> listCustomer() {
		return customerDao.findAll();

	}

}
