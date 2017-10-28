package com.ppi.model;

public class Login {
	
	private String username;
	private String password;
	private String role;
	private String status;
	private String salt;
	private String name;
	private String email;
	private String contact;
	
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	@Override
	public String toString() {
		return "Login [username=" + username + ", password=" + password + ", role=" + role + ", status=" + status
				+ ", salt=" + salt + ", name=" + name + ", email=" + email + ", contact=" + contact + "]";
	}
	
	
	
	
	
	/**
	 * @param username
	 * @param password
	 * @param role
	 * @param status
	 * @param salt
	 * @param name
	 * @param email
	 * @param contact
	 */
	public Login(Login login) {
		super();
		this.username = login.username;
		this.password = login.password;
		this.role = login.role;
		this.status = login.status;
		this.salt = login.salt;
		this.name = login.name;
		this.email = login.email;
		this.contact = login.contact;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Login(){
		
	}
	

}