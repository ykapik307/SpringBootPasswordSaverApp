package com.jwtAuthentication.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Application {

	@Id
	private String name;
	private String email;
	private String password;
	public Application(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Application() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Application [name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
	
}
