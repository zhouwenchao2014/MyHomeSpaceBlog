package com.zhou.blog.model;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String password;
	private String ipAddress;
	private String email;
	private String mobilePhone;

	public User(String id, String name, String password, String ipAddress, String email, String mobilePhone) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.ipAddress = ipAddress;
		this.email = email;
		this.mobilePhone = mobilePhone;
	}
	public User(){}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getEmail() {
		return email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
}
