package com.kp;

public class Customer {
	private int customerId;
	private String firstName;
	private String lastName;
	private long phoneNumber;
	private String emailId;
	private int stockIds[]; // int array containing only the stock ids
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int customerId, String firstName, String lastName, long phoneNumber, String emailId, int[] stockIds) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.stockIds = stockIds;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int[] getStockIds() {
		return stockIds;
	}

	public void setStockIds(int[] stockIds) {
		this.stockIds = stockIds;
	}

	
	
}


