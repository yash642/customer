package com.cg.customer.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.customer.entity.Customer;
import com.cg.customer.entity.Login;
import com.cg.customer.exception.NullCustomerException;
import com.cg.customer.service.LoginService;

@RestController
@RequestMapping(value = "/login")
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	private LoginService service;

//	@GetMapping(value = "/validateAdmin/{adminUserName}/{adminPassword}")
//	public Admin ValidateAdmin(@PathVariable String adminUserName, @PathVariable String adminPassword) {
//
//		Admin admin = this.service.getByadminUserName(adminUserName);
//		System.out.println(admin);
//		if (admin != null && admin.getAdminPassword().equals(adminPassword)) {
//			return admin;
//		}
//
//		return null;
//	}

	@PostMapping("/check")
	public Customer checkLogin(@RequestBody Login login) throws NullCustomerException {

		List<Login> loginlist = this.service.getAll();
		for (Login log : loginlist) {
			if (log.getUserName().equals(login.getUserName()) && log.getPassword().equals(login.getPassword())) {
				return log.getCustomer();
			}

		}
		throw new NullCustomerException("Failed Login");
	}

	@PutMapping("/update")
	public Login changePassword(@RequestBody Login login) {
		return this.service.update(login);

	}

	@PostMapping("/addcust")
	public Login addLogin(@RequestBody Login login) throws NullCustomerException, AddressException, MessagingException, IOException {
		return this.service.addLogin(login);
	}
	
//	@PutMapping("/reset/{username}/{pass}/{secans}")
//	public void resetpass(@PathVariable String username,@PathVariable String pass,@PathVariable String secans ) throws NullCustomerException, AddressException, MessagingException, IOException {
//		 this.service.resetPass(username, pass, secans);
//	}
	
	@GetMapping("/generateotp/{username}/{secans}")
	public int generateotp(@PathVariable String username,@PathVariable String secans) throws Exception {
		return this.service.validateandgenerateotp(username, secans);
	}
	
	@PutMapping("/changepass/{username}/{pass}/{b}")
	public Login changepass(@PathVariable String username,@PathVariable String pass,@PathVariable boolean b) throws NullCustomerException {
		return this.service.changepass(username, pass, b);
	}
	
	@GetMapping("/findall")
	public List<Login> findall(){
		return this.service.getAll();
	}
}
