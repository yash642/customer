package com.cg.customer.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer loginId;

	private String userName;

	private String password;

	private String securityans;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "custId")
	private Customer customer;

	public Login() {
		// TODO Auto-generated constructor stub
	}

	public Login(String userName, String password, Customer customer) {
		super();
		this.userName = userName;
		this.password = password;
		this.customer = customer;
	}

	public Login(String userName, String password, String securityans, Customer customer) {
		super();
		this.userName = userName;
		this.password = password;
		this.securityans = securityans;
		this.customer = customer;
	}

	public Integer getLoginId() {
		return loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getSecurityans() {
		return securityans;
	}

	public void setSecurityans(String securityans) {
		this.securityans = securityans;
	}

	@Override
	public String toString() {
		return "Login [loginId=" + loginId + ", userName=" + userName + ", password=" + password + ", customer="
				+ customer + "]";
	}

}
