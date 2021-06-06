package com.litige.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Delivery")
public class Delivery {
	private int deliveryId;
	private Date dateDelivery;
	private String tagNumber;
	private double weight;
	private String name;
	private String phone;
	private String address;
	private Agent agent;
	
	public Delivery() {
	}

	public Delivery(int deliveryId, Date dateDelivery, String tagNumber, double weight, String name, String phone,
			String address) {
		super();
		this.deliveryId = deliveryId;
		this.dateDelivery = dateDelivery;
		this.tagNumber = tagNumber;
		this.weight = weight;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}

	public Delivery(int deliveryId, Date dateDelivery, String tagNumber, double weight, String name, String phone,
			String address, Agent agent) {
		super();
		this.deliveryId = deliveryId;
		this.dateDelivery = dateDelivery;
		this.tagNumber = tagNumber;
		this.weight = weight;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.agent = agent;
	}

	@Id
	@Column(name = "deliveryID")
	public int getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}

	public Date getDateDelivery() {
		return dateDelivery;
	}

	public void setDateDelivery(Date dateDelivery) {
		this.dateDelivery = dateDelivery;
	}

	public String getTagNumber() {
		return tagNumber;
	}

	public void setTagNumber(String tagNumber) {
		this.tagNumber = tagNumber;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
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

	@ManyToOne
	@JoinColumn(name = "agentID")
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	@Override
	public String toString() {
		return "Delivery [deliveryId=" + deliveryId + ", dateDelivery=" + dateDelivery + ", tagNumber=" + tagNumber
				+ ", weight=" + weight + ", name=" + name + ", phone=" + phone + ", address=" + address + ", agent="
				+ agent + "]";
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Delivery) {
			Delivery another = (Delivery) obj;
			if (this.deliveryId == another.deliveryId) {
				return true;
			}
		}
		
		return false;
	}
	
	public int hashCode() {
		return new Long(this.deliveryId).intValue();
	}
	

}
