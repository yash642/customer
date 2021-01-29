package com.cg.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


@Entity
public class Customer {

	@Id
	@SequenceGenerator(name = "customer_id_sequence", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "customer_id_sequence", strategy = GenerationType.SEQUENCE)
	@Min(1)
	@Max(999)
	private Integer custId;

	
	@Size(min = 3, max = 25, message = "Enter Valid Customer Name")
	@Column(nullable = true, length = 255)
	private String custName;

	
	@Column(nullable = true, length = 255)
	private Integer age;

	
	@Column(nullable = true, length = 255)
	private String mobile;

	
	@Column(nullable = true, length = 255)
	private String emailId;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(@Size(min = 3, max = 25, message = "Enter Valid Customer Name")
			String custName, Integer age,
			String mobile, String emailId) {
		super();
		this.custName = custName;
		this.age = age;
		this.mobile = mobile;
		this.emailId = emailId;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", custName=" + custName + ", age=" + age + ", mobile=" + mobile
				+ ", emailId=" + emailId + "]";
	}

}
