package com.cg.customer.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin {
	
	@Id
	private String adminUserName;
	private String adminPassword;
	
	public String getAdminUserName() {
		return adminUserName;
	}
	public void setAdminUserName(String adminName) {
		this.adminUserName = adminName;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	
	public Admin() {
		super();
	}
	public Admin(String adminUserName, String adminPassword) {
		super();
		this.adminUserName = adminUserName;
		this.adminPassword = adminPassword;
	}
	@Override
	public String toString() {
		return "Admin [adminUserName=" + adminUserName + ", adminPassword=" + adminPassword + "]";
	}

	
	
}
