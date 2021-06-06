package com.litige.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Claim")
public class Claim {
	private int claimId;
	private String passengerName;
	private Date dateClaim;
	private int numberOfBags;
	private String passengerAddress;
	private String phone;
	private Agent agent;
	private Delivery delivery;
	
	public Claim() {
		
	}

	public Claim(int claimId, String passengerName, Date dateClaim, int numberOfBags, String passengerAddress,
			String phone) {
		this.claimId = claimId;
		this.passengerName = passengerName;
		this.dateClaim = dateClaim;
		this.numberOfBags = numberOfBags;
		this.passengerAddress = passengerAddress;
		this.phone = phone;
	}

	public Claim(int claimId, String passengerName, Date dateClaim, int numberOfBags, String passengerAddress,
			String phone, Agent agent, Delivery delivery) {
		this.claimId = claimId;
		this.passengerName = passengerName;
		this.dateClaim = dateClaim;
		this.numberOfBags = numberOfBags;
		this.passengerAddress = passengerAddress;
		this.phone = phone;
		this.agent = agent;
		this.delivery = delivery;
	}

	@Id
	@Column(name = "claimId")
	public int getClaimId() {
		return claimId;
	}

	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public Date getDateClaim() {
		return dateClaim;
	}

	public void setDateClaim(Date dateClaim) {
		this.dateClaim = dateClaim;
	}

	public int getNumberOfBags() {
		return numberOfBags;
	}

	public void setNumberOfBags(int numberOfBags) {
		this.numberOfBags = numberOfBags;
	}

	public String getPassengerAddress() {
		return passengerAddress;
	}

	public void setPassengerAddress(String passengerAddress) {
		this.passengerAddress = passengerAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@ManyToOne
	@JoinColumn(name = "agentID")
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	@ManyToOne
	@JoinColumn(name = "deliveryID")
	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	@Override
	public String toString() {
		return "Claim [claimId=" + claimId + ", passengerName=" + passengerName + ", dateClaim=" + dateClaim
				+ ", numberOfBags=" + numberOfBags + ", passengerAddress=" + passengerAddress + ", phone=" + phone
				+ ", agent=" + agent + ", delivery=" + delivery + "]";
	}

	public boolean equals(Object obj) {
		if (obj instanceof Claim) {
			Claim another = (Claim) obj;
			if (this.claimId == another.claimId) {
				return true;
			}
		}
		
		return false;
	}
	
	public int hashCode() {
		return new Long(this.claimId).intValue();
	}
	

}
