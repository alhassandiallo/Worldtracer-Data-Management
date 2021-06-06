package com.litige.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Agent")
public class Agent {
	private int id;
	private String name;
	private String phone;
	private String address;
	private String password;
	
	public Agent() {
		
	}

	public Agent(int id, String name, String phone, String address, String password) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.password = password;
	}
	
	@Id
	@Column(name = "agentId")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Override
	public String toString() {
		return "Agent [id=" + id + ", name=" + name + ", phone=" + phone + ", address=" + address + ", password="
				+ password + "]";
	}

	public boolean equals(Object obj) {
		if (obj instanceof Agent) {
			Agent another = (Agent) obj;
			if (this.id == another.id) {
				return true;
			}
		}
		
		return false;
	}
	
	public int hashCode() {
		return new Long(this.id).intValue();
	}
}
	