package com.litige.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Luggage")
public class Luggage {
	private int numBag;
	private String tagNumber;
	private String nameOnTag;
	private double receivedWeight;
	private String type;
	private String color;
	private String state;
	private Claim claim;
	private Flight flight;
	private Pilferage pilferage;
	private Agent agent;
	private Delivery delivery;
	private SendBag sendBag;
	
	
	
	public Luggage() {
		
	}



	public Luggage(int numBag, String tagNumber, String nameOnTag, double receivedWeight, String type, String color,
			String state) {
		this.numBag = numBag;
		this.tagNumber = tagNumber;
		this.nameOnTag = nameOnTag;
		this.receivedWeight = receivedWeight;
		this.type = type;
		this.color = color;
		this.state = state;
	}



	public Luggage(int numBag, String tagNumber, String nameOnTag, double receivedWeight, String type, String color,
			String state, Claim claim, Flight flight, Pilferage pilferage, Agent agent, Delivery delivery,
			SendBag sendBag) {
		this.numBag = numBag;
		this.tagNumber = tagNumber;
		this.nameOnTag = nameOnTag;
		this.receivedWeight = receivedWeight;
		this.type = type;
		this.color = color;
		this.state = state;
		this.claim = claim;
		this.flight = flight;
		this.pilferage = pilferage;
		this.agent = agent;
		this.delivery = delivery;
		this.sendBag = sendBag;
	}


	@Id
	@Column(name = "numBag")
	public int getNumBag() {
		return numBag;
	}



	public void setNumBag(int numBag) {
		this.numBag = numBag;
	}



	public String getTagNumber() {
		return tagNumber;
	}



	public void setTagNumber(String tagNumber) {
		this.tagNumber = tagNumber;
	}



	public String getNameOnTag() {
		return nameOnTag;
	}



	public void setNameOnTag(String nameOnTag) {
		this.nameOnTag = nameOnTag;
	}



	public double getReceivedWeight() {
		return receivedWeight;
	}



	public void setReceivedWeight(double receivedWeight) {
		this.receivedWeight = receivedWeight;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getColor() {
		return color;
	}



	public void setColor(String color) {
		this.color = color;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}


	@ManyToOne
	@JoinColumn(name = "claimID")
	public Claim getClaim() {
		return claim;
	}



	public void setClaim(Claim claim) {
		this.claim = claim;
	}


	@ManyToOne
	@JoinColumn(name = "coFlight")
	public Flight getFlight() {
		return flight;
	}



	public void setFlight(Flight flight) {
		this.flight = flight;
	}


	@ManyToOne
	@JoinColumn(name = "codePilf")
	public Pilferage getPilferage() {
		return pilferage;
	}



	public void setPilferage(Pilferage pilferage) {
		this.pilferage = pilferage;
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


	@ManyToOne
	@JoinColumn(name = "coSendBag")
	public SendBag getSendBag() {
		return sendBag;
	}



	public void setSendBag(SendBag sendBag) {
		this.sendBag = sendBag;
	}



	@Override
	public String toString() {
		return "Luggage [numBag=" + numBag + ", tagNumber=" + tagNumber + ", nameOnTag=" + nameOnTag
				+ ", receivedWeight=" + receivedWeight + ", type=" + type + ", color=" + color + ", state=" + state
				+ ", claim=" + claim + ", flight=" + flight + ", pilferage=" + pilferage + ", agent=" + agent
				+ ", delivery=" + delivery + ", sendBag=" + sendBag + "]";
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Luggage) {
			Luggage another = (Luggage) obj;
			if (this.numBag == another.numBag) {
				return true;
			}
		}
		
		return false;
	}
	
	public int hashCode() {
		return new Long(this.numBag).intValue();
	}

}
	




















