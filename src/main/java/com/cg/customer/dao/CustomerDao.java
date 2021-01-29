package com.cg.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.customer.entity.Customer;

/**
 * 
 * @author Shubham Sharma
 */
@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

}
