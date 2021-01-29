package com.cg.customer.service;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.cg.customer.entity.Login;
import com.cg.customer.exception.NullCustomerException;

public interface LoginService {

	//Admin getByadminUserName(String adminUserName);
	List<Login> getAll();
	Login update(Login login);
	Login addLogin(Login login) throws NullCustomerException, AddressException, MessagingException, IOException;
	//void resetPass(String username, String pass, String secans) throws NullCustomerException, AddressException, MessagingException, IOException;
	public int validateandgenerateotp(String username, String secans) throws Exception;
	public Login changepass(String username,String pass,boolean b) throws NullCustomerException;
}
