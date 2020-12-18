package com.abhiroop.emartviewapp.model;

public class Customer {

	int id;

	String name;

	String email;

	String status;
	public Customer() {
		super();
	}

	public Customer(String customerName, String email) {
		super();
		this.name = customerName;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String customerName) {
		this.name = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
