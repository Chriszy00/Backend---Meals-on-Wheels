package com.mow.DTO;

import com.mow.entity.RoleName;

public class UserRegistrationDTO {

	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	private String email;
	private String password;
	private RoleName role;
	
	public UserRegistrationDTO() {
	}



	public UserRegistrationDTO(String firstName, String lastName, String phoneNumber, String address, String email,
			String password, RoleName role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
		this.password = password;
		this.role = role;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public RoleName getRole() {
		return role;
	}



	public void setRole(RoleName role) {
		this.role = role;
	}
	
	

//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}


	
	
	
	
}