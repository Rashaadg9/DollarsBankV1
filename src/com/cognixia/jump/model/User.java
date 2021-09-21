package com.cognixia.jump.model;

public class User
{
	private int id;
	private String firstName;
	private String lastName;
	private String contact;
	private String dob;
	private String username;
	private String password;
	
	
	public User()
	{
		this(-1, "N/A", "N/A", "N/A", "N/A", "N/A", "N/A");
	}
	
	public User(int id, String firstName, String lastName, String contact, String dob, String username, String password)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contact = contact;
		this.dob = dob;
		this.username = username;
		this.password = password;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", contact=" + contact
				+ ", dob=" + dob + ", username=" + username + ", password=" + password + "]";
	}
	
}
